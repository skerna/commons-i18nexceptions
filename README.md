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


```java

    public StandardRuntimeException(String code, String message, Throwable cause, MessageCatalog catalog) {
        super(message, cause);
        this.reasonCode = code;
        this.catalog = catalog; // Catalogo
        this.additionalInfo = new ArrayList<>();
    }

```

El catálogo no es mas que un intermediario para cargar los mensajes
desde Bundle resouce, al disparar una exceptions estas se encargar
de buscar segun la locale.

```java
[code]=CODIGO_ITEM_NOTFOUND
[code]=CODIGO_ITEM_NOTFOUND
[message]=ITEM NO ENCONTRADO[message]=ITEM NO ENCONTRADO
[details]=[Product = null, database not ready]
[details]=[Product = null, database not ready]
[specific]=No se encontro
Caused by: java.lang.UnknownError
[specific]=No se encontro
	at StandardRuntimeException.<init>(StandardRuntimeException.java:44)
	at ExceptionCode.<init>(NotFoundException.java:26)
	at ResourceMessageCatalogTest.testRuntimeException(ResourceMessageCatalogTest.java:38)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
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