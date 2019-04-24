package ai.api;

public interface IAI{

	package ai;

	private IEngine engine;
    private Player playerOne;
    private Player playerTwo;
    private int team;

    public void simulation();

    //Il ne prend jamais la balle Problème
    public void doDefenseMoove(Point defensePlayer, Point ball ,int move);





    public void selectCloserPlayer(Player[] players);


    public void doAttackMoove(Point player, int move);

    public void doProtectMoove(Point protectPlayer, Point attackPlayer ,int move);

    public void selectProtectPlayer(Player[] players);

    public void selectAttackerPlayer(Player[] players);



}
