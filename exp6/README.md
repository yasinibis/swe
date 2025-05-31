# Website Comparison Tool

This project implements a website comparison tool that supports multiple comparison strategies using the Strategy and Observer design patterns.

## Project Structure

```
exp6/
├── ComparisonObserver.java    # Observer interface
├── ComparisonLogger.java      # Concrete observer implementation
├── ComparisonResult.java      # Class to store comparison results
├── ComparisonStrategy.java    # Strategy interface
├── ContentSizeStrategy.java   # Strategy for content size comparison
├── HtmlContentStrategy.java   # Strategy for HTML content comparison
├── Subject.java              # Subject interface for Observer pattern
├── TextContentStrategy.java   # Strategy for text content comparison
├── WebsiteComparator.java    # Main comparator class
└── Main.java                 # Demo class
```

## Coding Conventions

### Class Structure
- Interfaces are named with clear, descriptive names (e.g., `ComparisonStrategy`, `Subject`)
- Concrete classes implement interfaces with clear naming (e.g., `ContentSizeStrategy`, `HtmlContentStrategy`)


### Method Naming
- Methods that return boolean values start with 'is', 'has', or 'can'
- Getter methods start with 'get'
- Setter methods start with 'set'
- Action methods use clear verbs (e.g., `compare`, `addUrl`, `notifyObservers`)


## How to Run

```bash
javac exp6/*.java
```

```bash
java exp6.Main
```