package org.CA2;

public class AssetException extends Exception {
    /**
     * AssetException message string detailing the reason the exception was thrown.
     */
    private final String message;

    /**
     * Default constructor
     */
    public AssetException() {
        this.message = "Error in Exam or Exam subclass";
    }

    /**
     * Constructor specifying AssetException message
     * @param message String detailing the reason the exception was thrown
     */
    public AssetException(String message) {
        this.message = message;
    }

    /**
     * AssetException classes overridden toString function
     * @return A string representing the AssetException's message
     */
    public String toString() {
        return String.format("\n\t\tExamException: %s", this.message);
    }
}
