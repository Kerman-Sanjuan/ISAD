package ehu.isad.model;

public class webModel {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public webModel(String url, String md5, String version) {
        this.url = url;
        this.md5 = md5;
        this.version = version;
    }

    private String url;
    private String md5;
    private String version;


}
