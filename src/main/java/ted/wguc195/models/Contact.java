package ted.wguc195.models;

/**
 * Represents a contact.
 */
public class Contact {
    private int contactID;
    private String contactName;
    private String email;

    /**
     * Constructor
     * @param contactID The contact ID.
     * @param contactName The contact name.
     * @param email The contact email.
     */
    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * @return the contact ID.
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @param contactID the contact ID to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * @return the contact name.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contact name to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the contact email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the contact email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Converts the object to a human-readable string.
     * @return the contact name and ID in a human-readable string
     */
    public String toString() {
        return contactName + " | ID: " + contactID;
    }
}
