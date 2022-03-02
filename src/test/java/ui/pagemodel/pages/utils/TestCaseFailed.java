package ui.pagemodel.pages.utils;

/**
 * TestCaseFailed class extends Exception.
 */
public class TestCaseFailed extends Exception {
    /**
     * Added serializable varibale to remove warning.
     */
    private static final long serialVersionUID = 1L;
    private String message;

    /**
     * Constructor method.
     */
    public TestCaseFailed() {
        super();
    }

    /**
     * Constructor method with message.
     * @param message : String: message
     */
    public TestCaseFailed(String message) {
        super(message);
        this.message = message;
    }

}
