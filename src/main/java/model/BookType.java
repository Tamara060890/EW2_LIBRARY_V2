package model;

public enum BookType {
    TUTORIAL(1),           // Practical guides and how-to books
    REFERENCE(2),          // Reference works and documentation
    CONCEPTUAL(3),         // Theory and algorithms
    PROJECT_BASED(4),      // Books based on projects
    CAREER_SOFT_SKILLS(5), // Career development and soft skills
    TRENDS_FUTURE(6),      // Technology and future vision
    LANGUAGE_SPECIFIC(7),  // Books per programming language
    FRAMEWORK_TOOL(8);      // Books about tools and frameworks

    private final int referenceNumber;

    BookType(int referenceNumber){
        this.referenceNumber = referenceNumber;
    }

    public int getReferenceNumber() {
        return referenceNumber;
    }
}
