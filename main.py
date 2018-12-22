#!/usr/bin/env python
import re

s = """long multi-line string here"""
regex = "#(\d+) @ (\d+),(\d+): (\d+)x(\d+)"
compiled = re.compile(regex)

class Pattern:
    def __init__(self, id, left, top, width, height):
        self.id = int(id)
        self.left = int(left)
        self.top = int(top)
        self.width = int(width)
        self.height = int(height)
    def __str__(self):
        return "{0}, {1}, {2}, {3}, {4}".format(self.id, self.left, self.top, self.width, self.height)


def getParams(line):
    match = compiled.match(line)
    return match

patterns = []
for line in s.split("\n"):
    match = getParams(line)
    pattern = Pattern(match.group(1), match.group(2), match.group(3), match.group(4), match.group(5))
    patterns.append(pattern)

def getKey(width, height):
    return str(width) + ";" + str(height)


patches = {}
for pattern in patterns:
    for width in range(pattern.left + 1,pattern.left + pattern.width + 1):
        for height in range(pattern.top + 1, pattern.top + pattern.height + 1):
            key = getKey(width, height)
            if not pattern.id in patches:
                patches[pattern.id] = []
            patches[pattern.id].append(key)


coords = {}
for pattern in patterns:
    for width in range(pattern.left + 1,pattern.left + pattern.width + 1):
        for height in range(pattern.top + 1, pattern.top + pattern.height + 1):
            key = getKey(width, height)
            for id in patches.copy().keys():
                if id != pattern.id:
                    if key in patches[id]:
                        del patches[id]

print(patches)

#newDict = {k:v for (k,v) in coords.items() if v == 1}
#print(newDict)

