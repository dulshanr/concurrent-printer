package coursework;
/** ******************************************************************
 * File:      Document.java (CLASS)
 * Author:    K.A.D.S Ratnayake
 * Contents:  6SENG002W CWK
 *            This defines Document class where each document is
 *            owned by a student.
 * Date:      28/12/20
 * Version:   1.0
 ****************************************************************** */

public class Document {
    //document attributes
    private String docOwner;
    private String docTitle;
    private int pages;
//constructor
    public Document(String docOwner, String docTitle, int pages) {
        this.docOwner = docOwner;
        this.docTitle = docTitle;
        this.pages = pages;
    }
//getter methods.
    public String getDocOwner() {
        return docOwner;
    }


    public String getDocTitle() {
        return docTitle;
    }


    public int getPages() {
        return pages;
    }

}
