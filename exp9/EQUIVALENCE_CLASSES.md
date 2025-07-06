+ Equivalence Classes for WebMonitor System

++ 1. WebMonitor Class

+++ 1.1 Constructor

  What is Tested:
  - Good case: Creates WebMonitor instance correctly
  - Test: testWebMonitorConstructor() - Checks if WebMonitor was created

+++ 1.2 addNewUser Method

  Inputs:
  - name (String)
  - frequency (int) 
  - website (URI)
  - channel (ResponseChannel)

  Equivalence Classes:

  - name
    - Good case: not null, not empty (example: "Yasin") 
    - Bad case: null (throws NullPointerException) 
    - Edge case: empty string "" 

  - frequency
    - Good case: positive numbers (example: 5) 
    - Edge case: 0 
    - Edge case: negative numbers (example: -1) 

  - website
    - Good case: correct URI (example: "https://www.google.com") 
    - Bad case: null (throws NullPointerException) 

  - channel
    - Good case: real channel object (MailChannel, SmsChannel) 
    - Edge case: null 

  Test Cases:
  - testAddNewUserWithValidInput() - Tests good parameters
  - testAddNewUserWithNullName() - Tests null name
  - testAddNewUserWithEmptyName() - Tests empty name
  - testAddNewUserWithZeroFrequency() - Tests zero frequency
  - testAddNewUserWithNegativeFrequency() - Tests negative frequency
  - testAddNewUserWithNullWebsite() - Tests null website
  - testAddNewUserWithNullChannel() - Tests null channel

+++ 1.3 removeUser Method

  Inputs:
  - name (String)

  Equivalence Classes:

  - name
    - Good case: existing user (example: "Yasin") 
    - Good case: non-existing user (example: "NonExistent") 
    - Bad case: null (throws NullPointerException) 
    - Edge case: empty string 

  Test Cases:
  - testRemoveUser() - Tests removing existing user
  - testRemoveUserWithNonExistentUser() - Tests removing non-existent user
  - testRemoveUserWithNullName() - Tests null name
  - testRemoveUserWithEmptyName() - Tests empty name

+++ 1.4 setDefaultStrategy Method

  Inputs:
  - strategy (ComparisonStrategy)

  Equivalence Classes:
  - Good case: real strategy object (ContentSizeStrategy) 
  - Edge case: null 

  Test Cases:
  - testSetDefaultStrategy() - Tests good strategy
  - testSetDefaultStrategyWithNull() - Tests null strategy

+++ 1.5 attachWebsiteToUser Method

  Inputs:
  - name (String)
  - website (URI)

  Equivalence Classes:
  - Good case: existing user with good URI 
  - Edge case: null name 
  - Edge case: null website 

  Test Cases:
  - testAttachWebsiteToUser() - Tests good parameters
  - testAttachWebsiteToUserWithNullName() - Tests null name
  - testAttachWebsiteToUserWithNullWebsite() - Tests null website

+++ 1.6 attachNotificationChannel Method

  Inputs:
  - name (String)
  - channel (ResponseChannel)

  Equivalence Classes:
  - Good case: existing user with good channel 
  - Edge case: null name 
  - Edge case: null channel 

  Test Cases:
  - testAttachNotificationChannel() - Tests good parameters
  - testAttachNotificationChannelWithNullName() - Tests null name
  - testAttachNotificationChannelWithNullChannel() - Tests null channel

+++ 1.7 setWebsiteStrategy Method

  Inputs:
  - website (URI)
  - strategy (ComparisonStrategy)

  Equivalence Classes:
  - Good case: existing website with good strategy 
  - Edge case: null website 
  - Edge case: null strategy 

  Test Cases:
  - testSetWebsiteStrategy() - Tests good parameters
  - testSetWebsiteStrategyWithNullWebsite() - Tests null website
  - testSetWebsiteStrategyWithNullStrategy() - Tests null strategy

****

++ 2. User Class

+++ 2.1 Constructor

  Inputs:
  - name (String)
  - checkFrequency (int)
  - initialChannel (ResponseChannel)

  Equivalence Classes:

  - name
    - Good case: not null, not empty (example: "Yasin") 
    - Bad case: null (throws NullPointerException) 
    - Edge case: empty string 

  - checkFrequency
    - Good case: positive number (example: 5) 
    - Edge case: 0 
    - Edge case: negative (example: -1) 

  - initialChannel
    - Good case: real channel (MailChannel) 
    - Edge case: null 

  Test Cases:
  - testUserConstructorWithValidInput() - Tests good parameters
  - testUserConstructorWithNullName() - Tests null name
  - testUserConstructorWithEmptyName() - Tests empty name
  - testUserConstructorWithZeroFrequency() - Tests zero frequency
  - testUserConstructorWithNegativeFrequency() - Tests negative frequency
  - testUserConstructorWithNullChannel() - Tests null channel

+++ 2.2 addResponseChannel Method

  Inputs:
  - channel (ResponseChannel)

  Equivalence Classes:
  - Good case: real channel (SmsChannel) 
  - Edge case: null 

  Test Cases:
  - testAddResponseChannelWithValidChannel() - Tests good channel
  - testAddResponseChannelWithNullChannel() - Tests null channel

+++ 2.3 update Method

  Inputs:
  - message (String)

  Equivalence Classes:
  - Good case: normal string (example: "Test message") 
  - Edge case: null 
  - Edge case: empty string 
  - Edge case: very long message 
  - Edge case: special characters 
  - Edge case: unicode characters 

  Test Cases:
  - testUpdateWithValidMessage() - Tests good message
  - testUpdateWithNullMessage() - Tests null message
  - testUpdateWithEmptyMessage() - Tests empty message
  - testUpdateWithLongMessage() - Tests long message
  - testUpdateWithSpecialCharacters() - Tests special characters
  - testUpdateWithUnicodeCharacters() - Tests unicode characters

+++ 2.4 Interface Implementation

  Test Cases:
  - testUserImplementsObserver() - Checks if User implements Observer interface

****

++ 3. Subscription Class

  +++ 3.1 Constructor

  Inputs:
  - resourceUrl (URI)
  - strategy (ComparisonStrategy)

  Equivalence Classes:

  - resourceUrl
    - Good case: correct URI (example: "https://www.google.com") 
    - Bad case: null (throws NullPointerException) 

  - strategy
    - Good case: real strategy (ContentSizeStrategy) 
    - Edge case: null 

  Test Cases:
  - testSubscriptionConstructorWithValidInput() - Tests good parameters
  - testSubscriptionConstructorWithNullUri() - Tests null URI
  - testSubscriptionConstructorWithNullStrategy() - Tests null strategy

+++ 3.2 getResourceUrl Method

  Test Cases:
  - testGetResourceUrl() - Checks if returns correct URI

  +++ 3.3 Observer Management

  Inputs:
  - observer (Observer)

  Equivalence Classes:
  - Good case: real observer
  - Edge case: null 

  Test Cases:
  - testRegisterObserverWithValidObserver() - Tests good observer
  - testRegisterObserverWithNullObserver() - Tests null observer
  - testRemoveObserverWithValidObserver() - Tests removing good observer
  - testRemoveObserverWithNullObserver() - Tests removing null observer

+++ 3.4 notifyObservers Method

  Test Cases:
  - testNotifyObserversWithNoObservers() - Tests with no observers
  - testNotifyObserversWithObservers() - Tests with observers

  +++ 3.5 setComparisonStrategy Method

  Inputs:
  - strategy (ComparisonStrategy)

  Equivalence Classes:
  - Good case: real strategy (TextContentStrategy) 

  Test Cases:
  - testSetComparisonStrategy() - Tests good strategy

+++ 3.6 checkUpdate Method

  Test Cases:
  - testCheckUpdate() - Tests method execution

+++ 3.7 Interface Implementation

  Test Cases:
  - testSubscriptionImplementsSubject() - Checks if Subscription implements Subject interface

****

++ 4. Comparison Strategies

+++ 4.1 ContentSizeStrategy

  Inputs:
  - oldContent (String)
  - newContent (String)

  Equivalence Classes:

  - Same length strings
    - Good case: "Hello" vs "World" (returns true) 
    - Good case: "" vs "" (returns true) 
    - Good case: null vs null (returns true) 
    - Good case: very long strings 
    - Good case: special characters 
    - Good case: unicode characters 

  - Different length strings
    - Good case: "Hello" vs "Hello World" (returns false) 
    - Good case: "" vs "Hello" (returns false) 
    - Good case: null vs "Hello" (returns false) 
    - Good case: very long strings 
    - Good case: special characters 
    - Good case: unicode characters 

  Test Cases:
  - testContentSizeStrategyCompare() - Tests length comparison
  - testContentSizeStrategyGetStrategyName() - Tests name retrieval
  - testContentSizeStrategyWithNulls() - Tests null handling
  - testContentSizeStrategyWithLongStrings() - Tests long strings
  - testContentSizeStrategyWithSpecialCharacters() - Tests special characters
  - testContentSizeStrategyWithUnicode() - Tests unicode characters

+++ 4.2 TextContentStrategy

  Inputs:
  - oldContent (String)
  - newContent (String)

  Equivalence Classes:

  - Same text
    - Good case: "Hello World" vs "Hello World" (returns true) 
    - Good case: very long text 
    - Good case: special characters 
    - Good case: unicode text 

  - Different text
    - Good case: "Hello World" vs "Goodbye World" (returns false) 
    - Good case: very long text 
    - Good case: special characters 
    - Good case: unicode text 

  - HTML with same text content
    - Good case: "<html><body>Hello World</body></html>" vs "<div><p>Hello World</p></div>" (returns true) 
    - Good case: complex HTML 

  - Whitespace normalization
    - Good case: "Hello   World" vs "Hello World" (returns true) 

  Test Cases:
  - testTextContentStrategyCompare() - Tests text comparison
  - testTextContentStrategyGetStrategyName() - Tests name retrieval
  - testTextContentStrategyWithNulls() - Tests null handling
  - testTextContentStrategyWithLongText() - Tests long text
  - testTextContentStrategyWithSpecialCharacters() - Tests special characters
  - testTextContentStrategyWithUnicode() - Tests unicode text
  - testTextContentStrategyWithComplexHTML() - Tests complex HTML

+++ 4.3 HtmlContentStrategy

  Inputs:
  - oldContent (String)
  - newContent (String)

  Equivalence Classes:

  - Same HTML
    - Good case: "<html><body>Hello</body></html>" vs "<html><body>Hello</body></html>" (returns true) 
    - Good case: very long HTML 
    - Good case: special characters in HTML 
    - Good case: unicode in HTML 

  - Different HTML
    - Good case: "<html><body>Hello</body></html>" vs "<html><body>Goodbye</body></html>" (returns false) 
    - Good case: very long HTML 
    - Good case: special characters in HTML 
    - Good case: unicode in HTML 

  - HTML with different tags but same content
    - Good case: "<html><body>Hello</body></html>" vs "<div><p>Hello</p></div>" (returns true) 

  - Plain text
    - Good case: "Hello World" vs "Hello World" (returns true) 

  Test Cases:
  - testHtmlContentStrategyCompare() - Tests HTML comparison
  - testHtmlContentStrategyGetStrategyName() - Tests name retrieval
  - testHtmlContentStrategyWithNulls() - Tests null handling
  - testHtmlContentStrategyWithLongHTML() - Tests long HTML
  - testHtmlContentStrategyWithSpecialCharacters() - Tests special characters
  - testHtmlContentStrategyWithUnicode() - Tests unicode in HTML
  - testHtmlContentStrategyWithPlainText() - Tests plain text

+++ 4.4 Interface Implementation

  Test Cases:
  - testStrategiesImplementInterface() - Checks if all strategies implement ComparisonStrategy

****

++ 5. ResponseChannel Classes

+++ 5.1 MailChannel

  Inputs:
  - recipient (String)
  - message (String)

  Equivalence Classes:

  - recipient
    - Good case: normal string (example: "yasin@example.com") 
    - Edge case: null 
    - Edge case: empty string 
    - Edge case: special email formats 

  - message
    - Good case: regular text (example: "Test message") 
    - Edge case: null 
    - Edge case: empty string 
    - Edge case: very long message 
    - Edge case: special characters 
    - Edge case: unicode 

  Test Cases:
  - testMailChannelConstructor() - Tests constructor
  - testMailChannelSendNotificationWithValidInput() - Tests good input
  - testMailChannelSendNotificationWithNullRecipient() - Tests null recipient
  - testMailChannelSendNotificationWithNullMessage() - Tests null message
  - testMailChannelSendNotificationWithEmptyRecipient() - Tests empty recipient
  - testMailChannelSendNotificationWithEmptyMessage() - Tests empty message
  - testMailChannelSendNotificationWithLongMessage() - Tests long message
  - testMailChannelSendNotificationWithSpecialCharacters() - Tests special characters
  - testMailChannelSendNotificationWithUnicode() - Tests unicode
  - testMailChannelSendNotificationWithSpecialEmailAddresses() - Tests special email formats
  - testMailChannelImplementsInterface() - Checks if implements interface

+++ 5.2 SmsChannel

  Inputs:
  - recipient (String)
  - message (String)

  Equivalence Classes:

  - recipient
    - Good case: normal string (example: "+1234567890") 
    - Edge case: null 
    - Edge case: empty string 
    - Edge case: different phone formats 

  - message
    - Good case: regular text (example: "Test message") 
    - Edge case: null 
    - Edge case: empty string 
    - Edge case: very long message 
    - Edge case: special characters 
    - Edge case: unicode 

  Test Cases:
  - testSmsChannelConstructor() - Tests constructor
  - testSmsChannelSendNotificationWithValidInput() - Tests good input
  - testSmsChannelSendNotificationWithNullRecipient() - Tests null recipient
  - testSmsChannelSendNotificationWithNullMessage() - Tests null message
  - testSmsChannelSendNotificationWithEmptyRecipient() - Tests empty recipient
  - testSmsChannelSendNotificationWithEmptyMessage() - Tests empty message
  - testSmsChannelSendNotificationWithLongMessage() - Tests long message
  - testSmsChannelSendNotificationWithSpecialCharacters() - Tests special characters
  - testSmsChannelSendNotificationWithUnicode() - Tests unicode
  - testSmsChannelSendNotificationWithDifferentPhoneFormats() - Tests different phone formats
  - testSmsChannelImplementsInterface() - Checks if implements interface

+++ 5.3 Null Parameter Handling

  Test Cases:
  - testChannelsHandleNullsGracefully() - Tests both channels with null parameters
  - testChannelsHandleEmptyParametersGracefully() - Tests both channels with empty parameters
  - testChannelsHandleMixedParameters() - Tests mixed null and empty parameters
