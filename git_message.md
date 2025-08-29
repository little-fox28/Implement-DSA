# Git Commit Message Rules

This document defines the rules our team must follow for writing Git commit messages. The goal is to ensure consistency, readability, and compatibility with tools that generate changelogs and semantic versioning.

---

## Format (Conventional Commits)

Each commit message **must** follow this structure:

```
<type>[optional scope]!: <subject>

[optional body]

[optional footer(s)]
```

### Example

```
feat(auth): add email/password login endpoint

allow users to authenticate using email and password. tokens
are returned via the /auth/login route.

Closes #128
```

---

## Commit Message Parts

### 1. Type (required)

The type indicates the category of change:

* `feat`: a new feature (triggers a **minor** release)
* `fix`: a bug fix (triggers a **patch** release)
* `docs`: documentation changes only
* `style`: formatting, whitespace, semicolons, etc. (no code changes)
* `refactor`: code change that neither fixes a bug nor adds a feature
* `perf`: a performance improvement
* `test`: adding or updating tests only
* `build`: changes to build system or dependencies
* `ci`: changes to CI/CD configuration or scripts
* `chore`: other maintenance tasks
* `revert`: revert a previous commit

### 2. Scope (optional)

Specifies the area of the codebase affected. Use lowercase, kebab-case (e.g., `auth`, `user-profile`, `db`).

### 3. Breaking Change Indicator (optional)

* Add `!` after the type or scope if the commit introduces a breaking change.
* Include a `BREAKING CHANGE:` footer with migration instructions.

### 4. Subject (required)

* Short summary of the change (≤ 72 characters).
* Written in the **imperative mood** (e.g., "add", "fix", "update").
* Must start with a lowercase letter.
* No trailing period.

### 5. Body (optional)

* Explains **what** and **why**, not **how**.
* Each line wrapped at \~72 characters.

### 6. Footer(s) (optional)

* Reference issues: `Closes #123`, `Refs #456`
* Breaking changes: `BREAKING CHANGE: <explanation>`
* Co-authors: `Co-authored-by: Name <email>`

---

## Examples

### Feature

```
feat(cart): add discount code support

users can now apply discount codes at checkout.
```

### Bug Fix

```
fix(db): resolve connection leak in pool

connections were not being released in error cases.
Closes #42
```

### Breaking Change

```
feat(api)!: remove deprecated v1 endpoints

BREAKING CHANGE: clients must migrate to v2 endpoints under /v2.
see migration guide in docs/migrations/v1-to-v2.md.
```

### Documentation

```
docs(readme): add setup instructions for docker
```

---

## Quick Checklist (before committing)

* [ ] Message starts with a valid **type**
* [ ] Optional **scope** is kebab-case
* [ ] Subject line is ≤ 72 chars, imperative, lowercase, no period
* [ ] Breaking changes include `!` and `BREAKING CHANGE:` footer
* [ ] Body explains **what** and **why**
* [ ] Issues or references are in footer

