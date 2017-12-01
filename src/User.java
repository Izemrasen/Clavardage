public class User
{
    private String username;
    private String IPAddr;
    private int portNbr;

    public User(String username, String IPAddr, int portNbr)
    {
        this.username = username;
        this.IPAddr = IPAddr;
        this.portNbr = portNbr;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getIPAddr()
    {
        return IPAddr;
    }

    public int getPortNbr()
    {
        return portNbr;
    }
}
