# Design Notes

## Why ArrayList instead of an array?

The number of students, courses, and enrollments changes while the application is running. An array has a fixed length after creation, whereas `ArrayList` can grow as records are added. It also provides convenient operations such as `add`, `size`, and iteration. Services return a copy of each list so callers cannot add or remove records directly through the returned collection.

## Where are static members used, and why?

`IdGenerator` has static counters and static methods for generating student, course, and enrollment IDs. An ID counter is shared by all callers of that utility, so creating an object for each ID is unnecessary. This is suitable for this single-process, in-memory learning project; persistent applications would normally generate IDs through a database or another durable mechanism.

## Where is inheritance used, and what is gained?

`Student` and `Trainer` extend `Person`. `Person` contains common fields (`id`, `firstName`, `lastName`, and `email`) and a `getDisplayName()` method. Subclasses reuse this behavior and can specialize it by overriding `getDisplayName()`. Constructors use `super(...)` to initialize the base-class fields.

## Separation of concerns

- **Entity classes** represent data and enforce simple field validation.
- **Service classes** own `ArrayList` collections and business operations.
- **Main** displays menus, reads input, and calls services.
- **IdGenerator** centralizes ID creation.
- **Custom exceptions** represent missing entities and invalid-input categories.

## Validation and exception handling

`Main` catches malformed integer input and asks again. Service methods throw `EntityNotFoundException` for unknown IDs. Entity setters and services use `IllegalArgumentException` for invalid values or invalid operations, which the UI reports as a readable error.

## In-memory limitation

All records live in `ArrayList` instances. Closing the application clears them. Persistence is intentionally excluded to keep the project focused on Core Java fundamentals.
