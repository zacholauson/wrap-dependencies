# Wrapping Third Party Dependencies

## Run the app

`./gradlew`

## Upgrade to Client v2:


In: `./build.gradle`, change:

```
dependencies {
    compile project(':client-one')
}
```

To:

```
dependencies {
    compile project(':client-two')
}
```

## Upgrade to Client v3:


In: `./build.gradle`, change:

```
dependencies {
    compile project(':client-two')
}
```

To:

```
dependencies {
    compile project(':client-three')
}
```