package coursework;

public class Document {
    private String docOwner;
    private String docTitle;
    private int pages;

    public Document(String docOwner, String docTitle, int pages) {
        this.docOwner = docOwner;
        this.docTitle = docTitle;
        this.pages = pages;
    }

    public String getDocOwner() {
        return docOwner;
    }

    public void setDocOwner(String docOwner) {
        this.docOwner = docOwner;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
