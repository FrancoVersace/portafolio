import random
import string

def generar_contenido_aleatorio(longitud):
    caracteres = string.ascii_letters + string.digits + string.punctuation + ' \n'
    contenido = ''.join(random.choice(caracteres) for _ in range(longitud))
    return contenido

def generar_mensajes_aleatorios(num_mensajes, longitud_mensaje):
    mensajes = []
    for _ in range(num_mensajes):
        mensaje = generar_contenido_aleatorio(longitud_mensaje)
        mensajes.append(f"Mensaje: {mensaje}")
    return "\n".join(mensajes)

def generar_archivo_txt_aleatorio(nombre_archivo, num_mensajes=5, longitud_mensaje=50):
    # Agrega la extensión .txt al nombre si no la tiene
    if not nombre_archivo.endswith('.txt'):
        nombre_archivo += '.txt'
    
    # Genera los mensajes aleatorios
    contenido = generar_mensajes_aleatorios(num_mensajes, longitud_mensaje)
    
    # Escribe el contenido en el archivo
    with open(nombre_archivo, 'w') as archivo:
        archivo.write(contenido)
    
    print(f"Archivo '{nombre_archivo}' generado con contenido aleatorio.")

# Ejemplo de uso
nombre_archivo = "archivo_aleatorio"
generar_archivo_txt_aleatorio(nombre_archivo, num_mensajes=10, longitud_mensaje=100)
