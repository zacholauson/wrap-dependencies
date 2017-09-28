# Wrapping Third Party Dependencies

## Run the app

`./gradlew`

## Upgrade to Client v2:


In: `./build.gradle`, change:

```
dependencies {
    compile project(':client-v1')
}
```

To:

```
dependencies {
    compile project(':client-v2')
}
```

## Upgrade to Client v3:


In: `./build.gradle`, change:

```
dependencies {
    compile project(':client-v2')
}
```

To:

```
dependencies {
    compile project(':client-v3')
}
```