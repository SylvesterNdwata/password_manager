public class PasswordEntry {
    private String service;
    private String username;
    private String password;

    public PasswordEntry(String service, String username, String password) {
        this.service = service;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Your entry was: Service: " + this.service + ", Username: " + this.username + ", Password: " + this.password;
    }
}
