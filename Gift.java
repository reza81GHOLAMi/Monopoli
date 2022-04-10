public class Gift extends House{
    public Gift() {
        super(Color.White);
    }

    @Override
    void action(Player player) {
        player.budget+=200;
    }
}
