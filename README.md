NLS utility classes for Java.

# Main classes
## Nls.java
A ResourceBundle wrapper designed to be as short as possible for high code readability. 
Resource should be placed in src/main/resources mirroring your Java package structure, 
starting with `messages` followed by the locale (e.g., `messages_en.properties`, `messages_de.properties`).
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/util/example/
│   │       └── Example.java
│   └── resources/
│       └── com/util/example/
│           ├── messages.properties          # Default (English & fallback)
│           └── messages_de_DE.properties    # German
```
messages.properties:
```properties
key=Hello
key2=Hello {0}
```
messages_de_DE.properties:
```properties
key=Hallo
key2=Hallo {0}
```
Instantiate `Nls` by passing either the current class (`Example.class`) or the current instance (`this`), 
then invoke the `.get()` methods.

```java
class Example {

	// Option A: Instance-level usage
	private final Nls nls = new Nls(this);

	// Option B: Static-level usage
	// private static final Nls nls = new Nls(Example.class);
	
	private void print() {
		// Simple lookup (prints "Hello" or "Hallo" depending on Locale)
		System.out.println(nls.get("key1"));

		// Parameterized lookup using MessageFormat (prints "Hello Mike" or "Hallo Mike")
        System.out.println(nls.get("key2", "Mike")); 
	}
}
```
Note: If the key has no corresponding value, then the key is returned.

### Usage with Java 9 modules
If your application uses the Java Platform Module System (JPMS), open the package containing your NLS resources to the NLS module.
Inside module-info.java add:
`opens com.your.package to com.epau.util.nls;`

