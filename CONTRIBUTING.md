# Contributing to DoubleHashing

Thank you for your interest in contributing to the DoubleHashing project! We welcome contributions from the community and are pleased to have you join us.

## Table of Contents

1. [Getting Started](#getting-started)
2. [How to Contribute](#how-to-contribute)
3. [Development Setup](#development-setup)
4. [Code Style Guidelines](#code-style-guidelines)
5. [Testing](#testing)
6. [Pull Request Process](#pull-request-process)
7. [Code of Conduct](#code-of-conduct)
8. [Reporting Issues](#reporting-issues)
9. [Feature Requests](#feature-requests)

## Getting Started

Before you begin, ensure you have:

- A GitHub account
- Git installed on your local machine
- Java 11 or higher installed
- Maven 3.6 or higher installed

## How to Contribute

There are several ways to contribute to this project:

1. **Bug Reports**: Found a bug? Report it!
2. **Feature Requests**: Have an idea for a new feature?
3. **Code Contributions**: Submit pull requests with bug fixes or new features
4. **Documentation**: Improve documentation, tutorials, or examples
5. **Testing**: Write tests or help improve test coverage

## Development Setup

### Fork and Clone the Repository

1. Fork the repository on GitHub
2. Clone your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/DoubleHashing.git
   cd DoubleHashing
   ```

3. Add the upstream repository:
   ```bash
   git remote add upstream https://github.com/GZGef/DoubleHashing.git
   ```

### Set Up Development Environment

1. Ensure Java 11+ is installed:
   ```bash
   java -version
   ```

2. Ensure Maven is installed:
   ```bash
   mvn -version
   ```

3. Build the project:
   ```bash
   mvn clean compile
   ```

4. Run tests:
   ```bash
   mvn test
   ```

## Code Style Guidelines

### Java Code Style

- Follow standard Java coding conventions
- Use meaningful variable and method names
- Keep methods focused and concise
- Use Javadoc comments for all public APIs
- Follow the existing code style in the project

### Example Javadoc Format

```java
/**
 * Brief description of what the method does.
 *
 * @param param1 description of first parameter
 * @param param2 description of second parameter
 * @return description of return value
 * @throws ExceptionType description of exception
 */
public returnType methodName(ParamType param1, ParamType param2) throws ExceptionType {
    // implementation
}
```

### Naming Conventions

- **Classes**: PascalCase (e.g., `HashMap`, `OpenAddressHashTableDH`)
- **Methods**: camelCase (e.g., `put`, `get`, `remove`)
- **Variables**: camelCase (e.g., `key`, `value`, `hashTable`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `HASH_PARAM`, `REHASH_THRESHOLD`)
- **Interfaces**: PascalCase with descriptive names (e.g., `IHashTable`, `Entry`)

### File Organization

- Place source files in `src/main/java/com/github/gzgef/doublehashing/`
- Place test files in `src/test/java/com/github/gzgef/doublehashing/`
- One class per file (except for inner classes)
- Package names should be lowercase

## Testing

### Writing Tests

- All new code should include appropriate tests
- Tests should be placed in the `src/test/java/` directory
- Use JUnit 5 for writing tests
- Test both happy path and edge cases
- Ensure test names are descriptive

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=OpenAddressHashTableDHTest

# Run tests with coverage
mvn clean test jacoco:report
```

### Test Coverage

- Aim for at least 80% test coverage for new code
- Focus on testing critical paths and edge cases
- Use meaningful test data

## Pull Request Process

1. **Create a Branch**:
   ```bash
   git checkout -b feature/your-feature-name
   # or
   git checkout -b fix/your-bug-fix-name
   ```

2. **Make Your Changes**:
   - Write clear, concise commit messages
   - Follow the code style guidelines
   - Add or update tests as needed
   - Update documentation if necessary

3. **Test Your Changes**:
   ```bash
   mvn clean compile
   mvn test
   ```

4. **Commit Your Changes**:
   ```bash
   git add .
   git commit -m "Add feature: brief description"
   ```
   
   Commit message format:
   ```
   <type>(<scope>): <subject>

   <body>

   <footer>
   ```
   
   Types:
   - `feat`: New feature
   - `fix`: Bug fix
   - `docs`: Documentation changes
   - `style`: Code style changes (formatting, semicolons, etc.)
   - `refactor`: Code refactoring without changing behavior
   - `test`: Adding or updating tests
   - `chore`: Build process or auxiliary tool changes

5. **Push to Your Fork**:
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**:
   - Go to the GitHub repository
   - Click "New Pull Request"
   - Select your branch
   - Write a clear title and description
   - Reference any related issues
   - Submit the PR

7. **Code Review**:
   - Wait for code review
   - Address any feedback
   - Make necessary changes
   - Update the PR

## Code of Conduct

### Our Pledge

We are committed to providing a friendly, safe, and welcoming environment for all contributors, regardless of age, body size, disability, ethnicity, gender identity and expression, level of experience, education, socio-economic status, nationality, personal appearance, race, religion, or sexual identity and orientation.

### Our Standards

**Examples of behavior that contributes to creating a positive environment:**
- Using welcoming and inclusive language
- Being respectful of differing viewpoints and experiences
- Gracefully accepting constructive criticism
- Focusing on what is best for the community
- Showing empathy towards other community members

**Examples of unacceptable behavior:**
- The use of sexualized language or imagery and unwelcome sexual attention or advances
- Trolling, insulting/derogatory comments, and personal or political attacks
- Public or private harassment
- Publishing others' private information without explicit permission
- Other conduct which could reasonably be considered inappropriate in a professional setting

### Enforcement

Project maintainers are responsible for clarifying and enforcing our code of conduct and will take appropriate and fair corrective action in response to any behavior that they deem inappropriate, threatening, offensive, or harmful.

## Reporting Issues

### Bug Reports

When filing a bug report, please include:

1. **Summary**: Brief description of the problem
2. **Steps to Reproduce**: Detailed steps to reproduce the issue
3. **Expected Behavior**: What you expected to happen
4. **Actual Behavior**: What actually happened
5. **Environment**: 
   - Java version
   - Maven version
   - Operating system
   - Any other relevant environment details
6. **Screenshots**: If applicable, add screenshots to help explain the issue
7. **Additional Context**: Any other context about the problem

### Feature Requests

When requesting a new feature, please include:

1. **Problem Statement**: What problem does this feature solve?
2. **Proposed Solution**: How would you like this feature to work?
3. **Alternatives Considered**: Have you considered any alternative solutions?
4. **Additional Context**: Any other context or screenshots about the feature request

## Development Workflow

### Branch Naming

Use descriptive branch names:

- `feature/add-new-method` - for new features
- `fix/bug-description` - for bug fixes
- `docs/update-readme` - for documentation updates
- `refactor/improve-performance` - for refactoring

### Commit Messages

Write clear, concise commit messages:

```
feat: add support for custom hash functions

- Added IHashFunction interface
- Implemented default hash functions
- Updated HashMap to accept custom hash functions
- Added tests for custom hash functions
```

### Code Review Checklist

Before submitting a PR, ensure:

- [ ] Code follows project style guidelines
- [ ] All tests pass
- [ ] New code has appropriate test coverage
- [ ] Documentation is updated
- [ ] No unnecessary files are committed
- [ ] Commit messages are clear and follow conventions
- [ ] PR description is comprehensive

## Getting Help

If you need help or have questions:

1. Check the [README.md](README.md) for project documentation
2. Search existing [issues](https://github.com/GZGef/DoubleHashing/issues) for similar questions
3. Open a new issue with your question
4. Join the discussion in existing issues

## Recognition

Contributors will be recognized in the project's release notes and contributors list.

## License

By contributing to this project, you agree that your contributions will be licensed under the Apache License 2.0.

## Questions?

Feel free to reach out to the project maintainers with any questions or concerns.

---

**Thank you for your contribution!** 🎉