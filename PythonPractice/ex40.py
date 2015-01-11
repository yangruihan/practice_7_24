# -- coding: utf-8 --
#
#filename:'ex40.py'
#

cities = {
			'CA':'San Francisco',
			'MI':'Detroit',
			'FL':'Jacksonville'	
		 }
cities['NY'] = 'New York'
cities['OR'] = 'Portland'

def find_city(themap, state):
	if state in themap:
		return themap[state]
	else:
		return "Not found."

cities['_find'] = find_city

print cities

while True:
	print "State? (ENTER to quit)",
	state = raw_input("> ")

	if not state:
		break

	#this line is the most important ever! study!
	city_found = cities['_find'](cities, state)
	print city_found

for key in cities.items():
	print key[0]
	print key[1]

print cities.items()

