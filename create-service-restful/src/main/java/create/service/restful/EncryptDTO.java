package create.service.restful;

public class EncryptDTO {
    private String menssage;

    public EncryptDTO(String menssage) {
        this.menssage = menssage;
    }

    public String getMenssage() {
        return menssage;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }
}
