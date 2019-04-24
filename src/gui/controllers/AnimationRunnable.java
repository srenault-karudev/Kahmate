package gui.controllers;

import javax.swing.SwingWorker;

import gui.layers.*;

public class AnimationRunnable implements Runnable{

    private Layer[] layers;
    private long animationDuration;

    public AnimationRunnable(Layer[] layers, double animationDuration){
        this.layers = layers;
        this.animationDuration = (long)(animationDuration * 1000000000.0);
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        long lastTime = startTime;
        long time = 0;

        do{
            lastTime = System.nanoTime();
            time = lastTime - startTime;

            double alpha = (double)time / (double)this.animationDuration;

            for(Layer layer : this.layers){
                layer.setAlpha(alpha);
                layer.paintImmediately(layer.getVisibleRect());
            }
            
        } while(time < this.animationDuration);

        for(Layer layer : this.layers){
            layer.setAlpha(0.0);
        }
    }
}