# Michael-Ekstrom-IA08-Doodle

This repository holds all of the code neccessary to run my doodle project. To run it, go to Android studio and create a new project from version control, designating this repository as the target. To run the code, Marshmallow 64_x86 was used in conjuction with a Galaxy Nexus emulator.

The app works as a standard doodle app. The color selection is constrained to a Hue slider, so one can only use fully saturated colors. This gives a slightly juvenille color palette, but this fealt appropriate for a doodle app.

The special features are the undo/redo buttons, as well as the rotate option to their right. This allows you to rotate your current drawings around before drawing more stuff on top of it. The undo/redo functions will only affect drawing actions, they do not affect changes to the paint style, rotations, or clears. 

The majority of the required code was determined by the provided YouTube tutorials or by analyzing the Android documentation. However, particular help was gained from the following StackOverflow thread, which detailed how to properly rotate a Path.

//http://stackoverflow.com/questions/6763231/draw-rotated-path-at-particular-point
