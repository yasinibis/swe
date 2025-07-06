## Question 1: Virtual Machine vs Docker Container

A virtual machine (VM) is like a full computer. It has its own operating system and uses a lot of resources (like memory and CPU).
A Docker container is smaller and faster. It shares the same operating system as the host and only has what the application needs.
So, containers are lighter and start quickly, but VMs are more separated and secure.

## Question 2: Main Purpose of Continuous Integration

c) frequently integrate code into a shared repository and run automated tests

It means that we often add our code to a shared place and test it quickly to find problems early.

## Question 3:

How to Use

1. Build the Docker Image

Open a terminal in the project directory and run:

---docker build -t website-monitor .


2. Run the Website Monitor

---docker run --rm website-monitor https://example.com

3. Output

Starting Website Monitor for: https://example.com
Checking for updates...
Website monitoring completed.


## Question 4: Tool for Orchestrating CI/CD Pipelines

The correct answer is b) Jenkins.

Jenkins is the most commonly used tool for managing CI/CD pipelines. It helps automate the process of building, testing, and deploying software. While Kubernetes, Ansible, and Terraform are also important tools, Jenkins is specifically designed for CI/CD orchestration.

## Question 5: Strategies to Speed Up Testing Stage

1. Run tests in parallel 
2. Use test containers- Create smaller, focused test environments
3. Implement test caching - Save and reuse test results when possible
4. Split tests into groups - Run critical tests first, less important tests later
6. Optimize test data
7. Run tests only on changed code

## Question 6: Hard Question for Final Exam

1. Explain the concept of microservices architecture and a monolithic application

2. Explain the difference between unit testing, integration testing, and system testing. Give examples for each.

