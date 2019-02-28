# I18NExceptions

Esta libreria permite generar exceptions con soporte
para multiples idiomas

Objetos introducidos en tu sistema: 

- StandardException
- StandardRuntimeException

Los catalogos de idiomas pueden personalizarce en tus 
excepciones propias de tu applicación, para ellos debes
pasar el catalogo al heredar de standar exception o runtime exception

![BOLT](./docs/media/i8n-logo.png)



1 Paso uno, crea una extension del exception render

```kotlin
    object AwesomeCatalogo : ResourceMessageCatalog("") {
        override val bundleName: String
            get() = "scope_a.messages"
    }

```
2 Crea las exception root de tu app, en lugar de extender 
de runtime exception, extiende de StandardRuntimeException || StandardException

```java
    class ExceptionCode : StandardRuntimeException {
        construct(...)
        
        override fun getRender(): Render {
            return ExceptionRender(TestCatalog)
        }
    
    }

```
3 Usa

```kotlin
        
  throw ExceptionCode("CODIGO_ITEM_NOTFOUND")
            .appendErrorCode("CODIGO_ERROR_QUERY")
            .appendMessage("Algo de context en tu idioma ...")
            
```

4) Salida

```bash
    El item no fue enconrado en el sistema
    No se pudieron recuperar los datos
    Algo de context en tu idioma ...

```

# i18N

Para añadir soporte de nuevos idiomas, simplemente crea
el resource  con la extencion del idioma que buscas soportar.
Españos -> es
Ingles -> en 
etc....

```jshelllanguage
/project/src/main/resources/messages.properties
/project/src/main/resources/messages_es.properties
/project/src/main/resources/messages_en.properties
/project/src/main/resources/messages_uk.properties
```

messages.properties
```properties
MENSAJE_KEY_1=Hola !!
MENSAJE_KEY_2=Adios!!
```
messages_en.properties
```properties
MENSAJE_KEY_1=Hello!!
MENSAJE_KEY_2=GOODBYE!!
```