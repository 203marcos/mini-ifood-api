# Mini iFood API

Complete REST API for order management based on REST architecture, with JWT authentication, Redis cache and integration with PostgreSQL using JPA and Hibernate. The project follows best practices such as layered separation, automated testing and CI/CD pipeline.

---

## Objective

Demonstrate a professional implementation of an iFood-style order API, with JWT security, access control, relational persistence and automation with GitHub Actions. Ideal as technical portfolio for back-end Java/Spring developer positions.

---

## Technologies

| Technology | Version | Purpose |
|---|---|---|
| Java | 21 | Primary language |
| Spring Boot | 4.0.4 | Web framework |
| Spring Security | - | JWT Authentication |
| Spring Data JPA | - | ORM persistence |
| PostgreSQL | 15+ | Relational database |
| Flyway | 10+ | Schema versioning |
| JWT (JJWT) | 0.12.6 | Stateless tokens |
| H2 | 2.4+ | In-memory tests |
| Maven | 3.9+ | Build & dependency |
| GitHub Actions | - | CI/CD automated |
| Docker | 24+ | Containerization (future) |

---

## Architecture

```
src/main/java/com/marcosdias/miniifood/
├── auth/                        # Authentication and registration
│   ├── AuthController.java
│   ├── AuthService.java
│   └── dto/
├── security/                    # Security and JWT
│   ├── SecurityConfig.java
│   ├── JwtService.java
│   ├── JwtAuthenticationFilter.java
│   └── AppUserDetailsService.java
├── product/                     # Product management
│   ├── domain/Product.java
│   ├── repository/ProductRepository.java
│   ├── service/ProductService.java
│   ├── web/ProductController.java
│   └── web/dto/
├── user/                        # User management
│   ├── domain/User.java
│   ├── repository/UserRepository.java
│   ├── service/UserService.java
│   ├── web/UserController.java
│   └── web/dto/
└── MiniIfoodApiApplication.java
```

---

## Implemented Features

### Phase 1-3: Setup + User + Database
- [x] Spring Boot structure with Maven
- [x] User entity with JPA
- [x] Complete user CRUD
- [x] PostgreSQL + Flyway migrations
- [x] Environment with profiles (dev, test, prod)

### Phase 4: Security + JWT
- [x] Spring Security + BCrypt
- [x] JWT generation and validation
- [x] Endpoints /api/auth/register and /api/auth/login
- [x] Stateless JWT filter
- [x] Route protection with Bearer Token

### Phase 5: Products
- [x] Product entity with CRUD and pagination
- [ ] Phase 6: Order entity + OrderItem + Relationships
- [ ] Phase 7: Order status flow
- [ ] Phase 8: Payment mock
- [ ] Phase 9: Redis cache
- [ ] Phase 10: Complete testing
- [ ] Phase 11: Docker + docker-compose
- [ ] Phase 12: Documentation

---

## Quick Start

### Prerequisites
- JDK 21+
- Maven 3.9+
- PostgreSQL 15+ (or use dev profile with fallback)
- Git

### Local Installation

1. Clone the repository
   ```bash
   git clone https://github.com/203marcos/mini-ifood-api.git
   cd mini-ifood-api
   ```

2. Set environment variables (PowerShell)
   ```powershell
   $env:DB_HOST="localhost"
   $env:DB_PORT="5433"
   $env:DB_NAME="mini_ifood"
   $env:DB_USER="postgres"
   $env:DB_PASSWORD="your_postgres_password"
   $env:JWT_SECRET="your-secret-with-at-least-32-characters"
   ```

3. Run tests
   ```bash
   .\mvnw.cmd test
   ```

4. Run the application
   ```bash
   .\mvnw.cmd spring-boot:run
   ```

   API will be available at: http://localhost:8080

---

## API Endpoints

### Authentication
- `POST /api/auth/register` — Register new user
- `POST /api/auth/login` — Login and get JWT token

### Users (protected by Bearer Token)
- `GET /api/users` — List all users
- `GET /api/users/{id}` — Get user by ID
- `PUT /api/users/{id}` — Update user
- `DELETE /api/users/{id}` — Delete user

### Products (protected by Bearer Token)
- `GET /api/products` — List all products with pagination
- `GET /api/products/{id}` — Get product by ID
- `POST /api/products` — Create product
- `PUT /api/products/{id}` — Update product
- `DELETE /api/products/{id}` — Delete product

### Example usage with cURL

```bash
# Register
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Marcos","email":"marcos@email.com","password":"123456"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"marcos@email.com","password":"123456"}'

# Use token (copy the accessToken returned)
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer your_token_here"

# List products with pagination
curl -X GET "http://localhost:8080/api/products?page=0&size=10" \
  -H "Authorization: Bearer your_token_here"
```

---

## Tests

```bash
# Run all tests
.\mvnw.cmd test

# Run tests for a specific class
.\mvnw.cmd test -Dtest=ProductServiceTest

# Run with coverage report (future)
.\mvnw.cmd test jacoco:report
```

Current coverage: 20+ unit and integration tests (100% of Phase 1-5 features)

---

## Git Flow & CI/CD

### Branch Strategy
- `main` — Production-ready, merges only from develop
- `develop` — Base for features, always tested
- `feature/*` — Isolated feature development
- `fix/*` — Production fixes

### GitHub Actions
Workflow `.github/workflows/ci.yml` runs automatically on:
- Push to main, develop or feature/**
- Pull Request to main or develop

Validations:
- Build with Maven
- JUnit tests
- Linting (future: SonarQube)

---

## Important Notes

### About CI/CD structure
- CI (Continuous Integration) now: test + build validated on each push
- CD (Continuous Deployment) later: when integrating Docker/Kubernetes
- Consider separate branch `feature/ci-cd` for future pipeline refactoring

### About Spring profiles
- `dev`: PostgreSQL with environment variables, ideal for local development
- `test`: H2 in-memory, JUnit tests only (no PostgreSQL needed)
- `prod`: PostgreSQL requiring all variables without fallback, production-ready

---

## Security

- Passwords encrypted with BCrypt (never stored in plaintext)
- JWT tokens with configurable expiration
- CSRF disabled (stateless API)
- Input validation with @Valid + Jakarta Bean Validation
- HTTPS/TLS (when containerized)
- Rate limiting (future)

---

## Build & Deploy

### Local build
```bash
.\mvnw.cmd clean package
```

Generates: target/mini-ifood-api-0.0.1-SNAPSHOT.jar

### Docker (next phase)
```bash
docker build -t mini-ifood-api:latest .
docker run -p 8080:8080 \
  -e DB_HOST=postgres \
  -e DB_PASSWORD=postgres \
  -e JWT_SECRET=your-secret \
  mini-ifood-api:latest
```

---

## Roadmap

| Phase | Status | Description |
|---|---|---|
| 1 | Completed | Initial setup + structure |
| 2 | Completed | User entity + Repository + Service + Controller |
| 3 | Completed | PostgreSQL + Flyway migrations |
| 4 | Completed | Spring Security + JWT + Auth endpoints |
| 5 | Completed | Product entity + CRUD + Pagination |
| 6 | In progress | Order entity + OrderItem + Relationships |
| 7 | Planned | Order status flow + validations |
| 8 | Planned | Payment mock endpoint |
| 9 | Planned | Redis cache |
| 10 | Planned | Complete testing (unit + integration) |
| 11 | Planned | Docker + docker-compose |
| 12 | Planned | Documentation + cleanup

---

## Additional Documentation

- Swagger/OpenAPI available at /swagger-ui.html when app is running
- Migrations in src/main/resources/db/migration/
- Environment configuration in src/main/resources/application-*.yaml

---

## Contribution

This is a personal portfolio project. Suggestions and feedback are welcome.

---

## License

MIT License — see [LICENSE](LICENSE) for details.

---

## Author

Marcos — [GitHub](https://github.com/203marcos)

Developed to demonstrate expertise in Spring Boot, security and REST architecture.

---

Last update: March 2026

