#  Due to personal reasons, I couldn't attend this week's session, but the task was completed.

# Website Monitoring System

## How to Compile and Run

   ```
   javac website/monitor/*.java
   ```

   ```
   java website.monitor.WebsiteMonitor
   ```

   ```
   Update check complete.
   ```

## 2. Class Metrics Analysis

### Afferent Coupling (Ca), Efferent Coupling (Ce), and Instability (I) Metrics

#### WebsiteMonitor
- Afferent Coupling (Ca) = 0
  - No classes depend on this class
- Efferent Coupling (Ce) = 3
  - Depends on: Subscription, NotificationService, WebsiteChecker
- Instability (I) = Ce/(Ce + Ca) = 3/(3+0) = 1.0
  - Highly unstable class
  - This is expected as it's the main coordinator class

#### Subscription
- Afferent Coupling (Ca) = 2
  - Depended on by: WebsiteMonitor, NotificationService
- Efferent Coupling (Ce) = 1
  - Depends on: NotificationPreferences
- Instability (I) = 1/(1+2) = 0.33
  - Moderately stable class
  - Good balance between dependencies

#### NotificationPreferences
- Afferent Coupling (Ca) = 2
  - Depended on by: Subscription, NotificationService
- Efferent Coupling (Ce) = 0
  - No dependencies
- Instability (I) = 0/(0+2) = 0.0
  - Highly stable class
  - Good design as it's a simple data container

#### WebsiteChecker
- Afferent Coupling (Ca) = 1
  - Depended on by: WebsiteMonitor
- Efferent Coupling (Ce) = 0
  - No dependencies
- Instability (I) = 0/(0+1) = 0.0
  - Highly stable class
  - Well-encapsulated functionality

#### NotificationService
- Afferent Coupling (Ca) = 1
  - Depended on by: WebsiteMonitor
- Efferent Coupling (Ce) = 2
  - Depends on: Subscription, NotificationPreferences
- Instability (I) = 2/(2+1) = 0.67
  - Moderately unstable class
  - Could be improved by reducing dependencies

## 3. Package Structure

```
website.monitor/
├── core/
│   ├── WebsiteMonitor.java    # Main coordinator class
│   └── Subscription.java      # Subscription data model
├── notification/
│   ├── NotificationService.java       # Handles notification delivery
│   └── NotificationPreferences.java   # Notification configuration
├── checker/
│   └── WebsiteChecker.java    # Website update detection
└── util/
    └── HttpUtils.java         # HTTP utilities (to be extracted)
```

### Package Structure Explanation

1. **core package**
   - Contains the main business logic classes
   - WebsiteMonitor: Central coordinator
   - Subscription: Core data model

2. **notification package**
   - Isolates all notification-related functionality
   - Makes it easier to modify or extend notification mechanisms
   - Keeps notification preferences separate from core logic

3. **checker package**
   - Encapsulates website checking functionality
   - Makes it easy to modify or enhance the checking mechanism
   - Keeps HTTP-related code isolated

4. **util package**
   - Contains utility classes
   - Could include HTTP utilities extracted from WebsiteChecker
   - Makes the code more maintainable and testable


## 4. Options to Reduce Coupling

### 1. Interface Segregation
- Create interfaces for each major component:
  ```java
  public interface WebsiteChecker {
      boolean checkForUpdates(String url);
  }
  
  public interface NotificationService {
      void sendNotification(Subscription subscription);
  }
  ```
- This allows for easier mocking in tests and swapping implementations

### 2. Dependency Injection
- Instead of creating dependencies directly in WebsiteMonitor:
  ```java
  // Before
  public WebsiteMonitor() {
      this.notificationService = new NotificationService();
      this.websiteChecker = new WebsiteChecker();
  }
  
  // After
  public WebsiteMonitor(NotificationService notificationService, WebsiteChecker websiteChecker) {
      this.notificationService = notificationService;
      this.websiteChecker = websiteChecker;
  }
  ```

### 3. Event-Driven Architecture
- Implement an event system to decouple components:
  ```java
  public class WebsiteUpdateEvent {
      private final String url;
      private final String content;
  }
  
  public interface EventListener {
      void onEvent(WebsiteUpdateEvent event);
  }
  ```
- Components can subscribe to events they care about
- Reduces direct dependencies between components

### 6. Observer Pattern
- Implement observer pattern for notifications:
  ```java
  public interface WebsiteUpdateObserver {
      void onWebsiteUpdate(String url, String newContent);
  }
  ```
- Allows components to subscribe to updates without direct coupling

### 7. Command Pattern
- Use command pattern for actions:
  ```java
  public interface Command {
      void execute();
  }
  
  public class CheckWebsiteCommand implements Command {
      private final String url;
      private final WebsiteChecker checker;
  }
  ```
- Decouples the request from its execution

### Benefits of Reduced Coupling
1. Easier testing
2. More flexible system architecture
3. Better maintainability
4. Easier to extend functionality
5. Improved code reusability
