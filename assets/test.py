from pynput import keyboard


def on_press(key):
    if keyboard.Key.value == 104:
        print("ppp")

listener = keyboard.Listener(
    on_press=on_press)
listener.start()
