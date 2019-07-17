import RPi.GPIO as GPIO
import time
import threading
import os
import pygame
import requests
import json

payload = {'value' : '1'}
GPIO.setmode(GPIO.BCM)
check = True

def Sound_Run():
 pygame.mixer.init()
 sound = pygame.mixer.Sound("sound_effect.wav")
 print("gi")
 sound.play()

def Response_value():
 while(True):
  response = requests.get("http://192.168.137.209:8420/door").json()
  #response = json.dump(response)
  print(response)
  if str(response) in "1":
   print("rerere")
   Sound_Run()
   print("don't exit")
  else :
   print("gi")


def Run_module(cho_value):
 global check
 if cho_value < 100:
  
  check = False
  r = requests.post("http://192.168.137.209:8420/set", json=payload)

def left_cho():
 global check
 left_trig = 17
 left_echo = 27
 GPIO.setup(left_trig, GPIO.OUT)
 GPIO.setup(left_echo, GPIO.IN)
 
 while check:
  GPIO.output(left_trig, False)
  time.sleep(0.5)
  GPIO.output(left_trig, True)
  time.sleep(0.00001)
  GPIO.output(left_trig, False)

  while GPIO.input(left_echo) == 0:
   pulse_start_left = time.time()
  while GPIO.input(left_echo) == 1:
   pulse_end_left = time.time()

  pulse_duration_left = pulse_end_left - pulse_start_left

  left_distance = pulse_duration_left * 17000
  left_distance = round(left_distance, 2)

  print("left_distance : ", left_distance, "cm")

  Run_module(left_distance)	
def right_cho():
 global check
 right_trig =23 
 right_echo = 24

 GPIO.setup(right_trig, GPIO.OUT)
 GPIO.setup(right_echo, GPIO.IN)
 
 while check:
  GPIO.output(right_trig, False)
  time.sleep(0.5)
  GPIO.output(right_trig, True)
  time.sleep(0.00001)
  GPIO.output(right_trig, False)

  while GPIO.input(right_echo) == 0:
   pulse_start_right = time.time()
  while GPIO.input(right_echo) == 1:
   pulse_end_right = time.time()

  pulse_duration_right = pulse_end_right - pulse_start_right

  right_distance = pulse_duration_right * 17000
  right_distance = round(right_distance, 2)

  print("right_distance : ", right_distance, "cm")
  Run_module(right_distance)

def left_cho_thread():
 thread = threading.Thread(target=left_cho())
 thread.daemon = True
 thread.start()

def right_cho_thread():
 thread = threading.Thread(target=right_cho)
 thread.daemon = True
 thread.start()

def Response_Value_thread():
 thread = threading.Thread(target=Response_value())
 thread.daemon = True
 thread.start()

if __name__ == "__main__":
 os.system("sh mjpg.sh")
 Response_Value_thread()
 while(True):
  right_cho_thread()
  left_cho_thread()
