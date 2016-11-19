'''
	@ AUTHOR NAME HERE
	@ Starter Code By Harris Christiansen (Harris@purduecs.com)
	2016-01-28
	For: Purdue Hackers - Battleship
	Battleship Client
'''

import sys
import socket
import time
import random

API_KEY = "764488458" ########## PUT YOUR API KEY HERE ##########

GAME_SERVER = "battleshipgs.purduehackers.com"

##############################  PUT YOUR CODE HERE ##############################

letters = ['A','B','C','D','E','F','G','H']
grid = [[-1 for x in range(8)] for x in range(8)] # Fill Grid With -1s

storedPlacements = []


def getRandomDirection():
	return random.getrandbits(1)

def generateRandomRowCol():
	row = random.randint(0, 7);
	column = random.randint(0, 7);
	return row, column

def generateCoordinates(len):
	good = False
	# Until a valid move is generated
	while ~good:
		moveList = []
		amt = 0
		row, column = generateRandomRowCol()
		direction = getRandomDirection()

		# Generate a moveList
		while amt < len:
			#If vertical, add to row
			if direction:
				moveList.append((row + 1, column))
			# If horizontal, add to column
		else:
			moveList.append((row, column + 1))


		good = isPlacementValid(moveList)

	low  = letters[moveList[0][0]] + moveList[0][1] 
	high = letters[moveList[len - 1][0]] + moveList[len - 1][1]
	return low, high


# 0-3, a-d

def firstHop():

def randomHop(colStart, colEnd, rowStart, rowEnd):


def placeShips(opponentID):
	global grid
	# Fill Grid With -1s
	grid = [[-1 for x in range(8)] for x in range(8)] # Fill Grid With -1s

	# Place Ships
	low, high = generateCoordinates(2)
	placeDestroyer(low, high) # Ship Length = 2
	
	low, high = generateCoordinates(3)
	placeSubmarine(low, high) # Ship Length = 3
	
	low, high = generateCoordinates(3)
	placeCruiser(low, high) # Ship Length = 3

	low, high = generateCoordinates(4)
	placeBattleship(low, high) # Ship Length = 4

	low, high = generateCoordinates(5)
	placeCarrier(low, high) # Ship Length = 5

def makeMove():
	global grid
	for x in range(0,8): # Loop Till Find Square that has not been hit
		for y in range(0,8):
			if grid[x][y] == -1:
				wasHitSunkOrMiss = placeMove(letters[x]+str(y)) # placeMove(LetterNumber) - Example: placeMove(D5)

				if(wasHitSunkOrMiss == "Hit" or wasHitSunkOrMiss == "Sunk"):
					grid[x][y] = 1
				else:
					grid[x][y] = 0

				return

def erasePlacements():
	storedPlacements = []

def isPlacementValid(move):
	for i in range(move.length):
		for j in range(storedPlacements.length):
			if move[i] == storedPlacements[j]:
				return False

	for i in range(move.length):
		storedPlacements.append(move[i])
	return True

############################## ^^^^^ PUT YOUR CODE ABOVE HERE ^^^^^ ##############################

def sendMsg(msg):
	global s
	try:
		s.send(msg)
	except:
		s = None

def connectToServer():
	global s
	invalidKey = False
	try:
		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		s.connect((GAME_SERVER, 23345))

		sendMsg(API_KEY)
		data = s.recv(1024)

		if("False" in data):
			s = None
			print "Invalid API_KEY"
			invalidKey = True
	except:
		s = None

	if invalidKey:
		sys.exit()


destroyer=submarine=cruiser=battleship=carrier=("A0","A0")
dataPassthrough = None

def gameMain():
	global s, dataPassthrough, moveMade
	while True:
		if(dataPassthrough == None):
			if s == None:
				return
			data = s.recv(1024)
		else:
			data = dataPassthrough
			dataPassthrough = None

		if not data:
			s.close()
			return
		
		if "Welcome" in data: # "Welcome To Battleship! You Are Playing:xxxx"
			welcomeMsg = data.split(":")
			placeShips(welcomeMsg[1])
			if "Destroyer" in data: # Only Place Can Receive Double Message, Pass Through
				dataPassthrough = "Destroyer(2):"
		elif "Destroyer" in data: # Destroyer(2)
			sendMsg(destroyer[0])
			sendMsg(destroyer[1])
		elif "Submarine" in data: # Submarine(3)
			sendMsg(submarine[0])
			sendMsg(submarine[1])
		elif "Cruiser" in data: # Cruiser(3)
			sendMsg(cruiser[0])
			sendMsg(cruiser[1])
		elif "Battleship" in data: # Battleship (4)
			sendMsg(battleship[0])
			sendMsg(battleship[1])
		elif "Carrier" in data: # Carrier(3)
			sendMsg(carrier[0])
			sendMsg(carrier[1])
		elif "Enter" in data: # Enter Coordinates
			moveMade = False
			makeMove()
		elif "Error" in data: # Error: xxx
			print("Received Error: "+data)
			sys.exit()
		elif "Hit" in data or "Miss" in data or "Sunk" in data:
			print("Error: Please Make Only 1 Move Per Turn.")
			sys.exit()
		elif "Die" in data:
			print("Error: Your client was disconnected using the Game Viewer.")
			sys.exit()
		else:
			print("Received Unknown Response: "+data)
			sys.exit()


def placeDestroyer(startPos, endPos):
	global destroyer
	destroyer = (startPos.upper(), endPos.upper())
def placeSubmarine(startPos, endPos):
	global submarine
	submarine = (startPos.upper(), endPos.upper())
def placeCruiser(startPos, endPos):
	global cruiser
	cruiser = (startPos.upper(), endPos.upper())
def placeBattleship(startPos, endPos):
	global battleship
	battleship = (startPos.upper(), endPos.upper())
def placeCarrier(startPos, endPos):
	global carrier
	carrier = (startPos.upper(), endPos.upper())

def placeMove(pos):
	global dataPassthrough, moveMade
	if moveMade: # Only Make 1 Move Per Turn
		print("Error: Your client was disconnected using the GameViewer")
		sys.exit()

	moveMade = True
	sendMsg(pos)
	data = s.recv(1024)
	if "Hit" in data:
		return "Hit"
	elif "Sunk" in data:
		return "Sunk"
	elif "Miss" in data:
		return "Miss"
	else:
		dataPassthrough = data
		return "Miss"

while True:
	connectToServer()
	if s != None:
		try:
			gameMain()
		except socket.error, msg:
			None
	time.sleep(1)
