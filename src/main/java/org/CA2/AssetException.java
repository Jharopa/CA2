package org.CA2;

public class AssetException extends Exception{
    private final String message;

    public AssetException() {
        this.message = "Error in Exam or Exam subclass";
    }

    public AssetException(String message) {
        this.message = message;
    }

    public String toString() {
        return String.format("\n\t\tExamException: %s", this.message);
    }
}
