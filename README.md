# Reflections
## Name: Gusti Faturahman Rais | NPM: 2206821241

# Assignment 1

### Reflection 1
1. For my code, I believe I have not set up the environment to be 100% correct. For most of my methods, variables, and so on, I gave them meaningful names that will hopefully make the reader easily understand what each code is supposed to be. I believe the functions I employed are effective in the sense that they are properly named and mostly serve one function. When it comes to comments, I believe I have not given it my all here. The comments I wrote for this module were only written when I 'felt' like it. I know that is not good practice and will be sure to improve in the future. Moving onto secure coding practices, I believe I have not implemented that many secure coding practices. I believe the only practice I employed was testing everything correctly as I went on. My code as a whole I believe can be so much better. I need to get the hang of writing tests mostly, whether it be unit or functional, I hope that I can write better and easier-to-understand code in the future.

### Reflection 2
1. After Writing the unit test, I felt relieved that the tests I wrote ran. I am not sure if I met all the specifications required in the module. I hope that with more time I can get to better understand tests and implement them in my code. Regarding testing itself, I think the number of tests depends on the features of the app. It is better for the app and development if all the features of the unit test are written. This I believe is code coverage, the percentage of the source code that is tested. 100% code coverage does not ensure the program is free of bugs. The features may work by themselves but have bigs when interacting with each other. This is a disadvantage of unit tests.
2. For this question I seem to have misread the exercise and did both item and quantity for my test. If I were to have made just the product name and I needed to find the quantity in the test, I would change the `productNameInput.sendKeys();` to be the quantity and change the element to `By.id("quantityInput")`. I would make the same changes for reading and verifying. I think the code won't get messier. It will stay relatively clean

# Assignment 2

HTTP: https://eshop-goosetea04.koyeb.app/product/list

Code coverage before running new tests: 37% | Now: 58%

### Reflection 1

When I initially ran pmd.yml that gave me the log with errors, there was just one code quality issue I fixed. I fixed the unused import `org.springframework.boot.Banner` in `ProductController.java`. After I deployed the website, I fixed the remaining quality issues. I first noticed that there is unused import `org.springframework.web.bind.annotation.*`. I thought it would be easy of a fix to just erase it. I then saw that 5 methods used it actually, so the issue was the `*` that was importing eveything else that wasn't used. I fixed it by replacing the `*` with the 5 methods that I know I am using in the file. These are `RequestMapping`, `GetMapping`, `PostMapping`, `ModelAttribute`, `PathVariable`. Next, I saw that in `ProductService.java`, there is a redundancy regarding the `public` tage since the interface is already public. I fixed this by removing `public` from the methods in the interface. AFter I made these changes, I checked the log generated by `pmd.yml` again and I saw that these issues are resolved.

### Reflection 2

In my opinion, I have applied the concepts of CI well in my github workflows. Continuous integration (CI) is a software development practice where code changes from multiple contributors are automatically integrated into a shared repository several times a day. In my case, my GitHub workflows are automatically triggering when changes are pushed to the repository. This means that whenever I push to github, I run unit tests and perform static code analysis. Since this is an implementation of me using CI, this will surely improve my coding ability in the future.

# Assignment 3

### Reflection 1

The SOLID Principles I applied to my code are S (ingle Responsibility Principle), O (Open-Closed Principle), and D (Dependency Inversions Principle). I did not find suitable problems and fixes regarding L (Lizkov-substitution Principle) and I (Inverse Segregation Principle)

1. **Single Responsibility Principle** - I separated the CarController and the ProductController that were initially grouped into one .java file. We do this since we know that one class should only have one responsibility, hence why I separataed the two.
2. **Open-Closed principle** - I noticed that THe repositories CarRepository and ProductRepository had similar functions and uses. I generalised them both to implement from RepositoryBase(). This is so that if we want to modify or add a repository, we can utilise the base repo.
3. **Dependency-Inversions Principle** - This principle is another contributing factor as to why I split CarController and ProductController. We gravitate towards more abstraction compared to concrete implementation.

### Reflection 2

There are many advantages of having SOLID Principles put in place over not. 

We know that well-organized code leads to more efficient debugging, reading, and testing processes, as demonstrated by breaking down classes like CarController and ProductController. Additionally, **the Single Responsibility Principle (SRP)** ensures that functions have a clear and singular purpose, enhancing maintainability and readability by allowing developers to understand their functionality simply by examining their names. 

Additionally, having SOLID also removes unnecessary inheritance. Avoiding unnecessary multilevel inheritance, such as having `CarController.java` inherit from `ProductController.java`, helps prevent confusion about class attributes, maintaining code clarity and simplicity. 

Another advantage lies in the ease of developing new features without disrupting existing code and tests. For instance, creating new interfaces for classes with similar functionality enables smoother implementation and comparison of future features.

Lastly, **the Interface Segregation Principle (ISP)** underscores the importance of intuition over code length, suggesting that splitting interfaces with more representative names leads to quicker comprehension of their purposes. These benefits collectively contribute to creating cleaner, more maintainable, and scalable software systems.

In conclusion, implementing SOLID has alot of advantages in the coding development environment. These benefits are more felt when working with multiple programmers and also while passing down responsibilities to other programmers. The level of intuition and clarity provided provide efficiency to the workflow.

### Reflection 3 

A major problem that SOLID brings is Overcomplexity and Overnengineering. Someone wanting to modfiy our code may find it easier to follow but the implementation they would have to do to achieve those modifciations would be much more than if we were to not apply solid principles. Furthermore, SOLID also encourages abstraction so that our program will be too abstract meaning, that over time, our codebase might be harder to understand and maintain.

# Assignment 4

## Reflection 1

### Do I have enough Functional tests to reassure myself that my application really works, from the point of view of the user?

Up to now, we have only made unit tests. There are no functional tests that we made, so I cannot say that there are enough functional tests implemented to cover all functionalities typically that will be done by the user. This is because we did not implement a controller for Payment and Order.

### Am I testing all the edge cases thoroughly?

For the most part, I believe that I have covered all of the edge cases for each function in my repository, service ets. In the tests folder, we can see that all the functions correlate to some function in `java/main`. This signifies to me that all of my tests cover all possible edge cases. In all of my implementation of fields and whatnot, there are tests to see if the field is valid and invalid. In addition to that, there are tests for creation, deletion and update. These are all actions the typical user will do.

### Do I have tests that check if all my components fit together properly? Could some integrated tests do this, or are functional tests enough?

Again, we have not made any functional tests, since we have not implement a controller for neither the `Payment` class nor the `Order` class. So far, the unit tests are just individual features of what a user might do in our program, but as far as joined processes and use cases for each user, we have not done that yet.

### Are my tests giving me the confidence to refactor my code, fearlessly and frequently?

Yes, I believve that with the tests I have now for the classes I have now, I am able to change small details regarding the assertion with something from the  `enums` library and have the test still work. With the way I implemented my assertions, I am confident that all the changes are with refactoring, should not affect the utcome of the test. For example, I change the content og "Voucher" with `PaymentMethod.SUCCESS.getValue()`, the `assertEqual()` assertion will still work.

### Are my tests helping me to drive out a good design? If I have a lot of integration tests but less unit tests, do I need to make more unit tests to get better feedback on my code design?

I've tested every part of the Order feature thoroughly, even checking for things that shouldn't really happen if the controller and interface are built right (like orders that don't exist, which shouldn't be possible since we only allow viewing orders, not deleting them). This thorough testing helps me understand my code design better, so when I make changes, I can easily spot any errors.

### Are my feedback cycles as fast as I would like them? When do I get warned about bugs, and is there any practical way to make that happen sooner?

Due to the CI/CD that we implemented in the previous modules, I am supposed to get notified about bugs in my code. However, there are problems with my CI/CD after I refactored some of my classes to implement SOLID. These changes have caused some problems in my CI/Cd that I have not fully fixed. Currently, I am notified of bugs in the problems section of Intellij. This is by no means an effective way of finding out bugs, but that sgould be resolved when I fix my CI/CD code.

### Is there some way that I could write faster integration tests that would get me feedback quicker?

As mentioned before, there are no integration tests.

### Can I run a subset of the full test suite when I need to?

We can choose which tests we want to run. We can run each test class individually by just running the tests on Intellij. Furthermore, each test is grouped based on its functionality. If we want to run all `Service` tests, we just need to run the Service folder. If we want to run the whole test suite, thats available as well.

### Am I spending too much time waiting for tests to run, and thus less time in a productive flow state?

I do not believe that my tests are taking too long to run. The tests are maximum 2.5 seconds to run, and this is great because it means there are more chances for us to be productive.

## Is F.I.R.S.T. implemented?

- Fast: As mentioned before, our max time to run a test is 2.5 seconds. This is achieved by mocking.

-  Isolated: The tests made dont depend on any other datta in any other classes or tests. Each assert and test are just implemente dand evaluated in their own test suites.

-  Repeatable: Yes, they are able to be repeated as many times as desired since they do not depend on any external factors, and as mentioned before, they are not affected y other tests.

-  Self-validating: With all of the assertions we have to consider a test a test, it validates all answers desired from each test based on whether it is True, False, Equal, Nul etc. This signifies that our tests are self-validating.

-  Thorough: I believe that all the tests I made are thorough in the sense that it covers all the use cases of each desired feature in the java class. This is then verified by making the class and seeing that it really does cover all aspects desired. 
