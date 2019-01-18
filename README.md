# README

## Requerimiento para poder ejecutar la aplicación

Para esto se debe de tener instalado en la maquina java JDK 8 o superiror.

## Ejecución de la applicación

Para esto se debe de utilizar el commando:

``` shell
    java -jar bbva-encryption-0.0.1-SNAPSHOT.jar
```

### Durante la ejecución

Durante la ejecución la applicación solicitará que clase de paso se desea seguir.

Estos pasos pueden ser: 
#### *ENCRYPT_3DES_KEY* : 
Cuando se ingresa este valor, el usuario esta indicando que desea ecryptar una llave
la cual será utilizada en otra applicación posteriormente. 
#### *DECRYPT_3DES_KEY* :
Cuando se ingresa este valor, el usuario esta indicando que desea desencryptar esta llave
ya sea por que se requiere reemplazar el factor de encrypción *KEK* o se desea reemplazar
la llave por una nueva.

### ENCRYPT_3DES_KEY

Cuando el usuario selecciona esta opcion la applicación solicita 3 valores:

#### Input 3Des Key
Este valor es la llave con la cual se ecriptarán las tarjetas.

#### Input KEK key
Este valor se encuentra en la base de datos *yp_bbva_colombia_cde_tv* en la tabla *yp_kek*
y repesenta el valor de la columna *key*

#### Input KEK iv
ste valor se encuentra en la base de datos *yp_bbva_colombia_cde_tv* en la tabla *yp_kek*
y repesenta el valor de la columna *iv*

Al finalizar el proceso de ecripción, este retornará la cadena de texto equivalente al valor ecriptado

### DECRYPT_3DES_KEY

Cuando el usuario selecciona esta opcion la applicación solicita 3 valores:

#### Input Encrypted key
Este valor es la llave encryptada.

#### Input KEK key
Este valor se encuentra en la base de datos *yp_bbva_colombia_cde_tv* en la tabla *yp_kek*
y repesenta el valor de la columna *key*

#### Input KEK iv
ste valor se encuentra en la base de datos *yp_bbva_colombia_cde_tv* en la tabla *yp_kek*
y repesenta el valor de la columna *iv*

Al finalizar el proceso de desencrición, este retornara el valor original de la llave.