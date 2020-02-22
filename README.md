# godot-kotlin-sqlite

## !!!DISCLAIMER !!! WORK IN PROGRESS!!!

This repository is a port of the [Godot SQLite plugin](https://github.com/2shady4u/godot-sqlite) from godot-cpp to [godot-kotlin](https://github.com/utopia-rise/godot-kotlin) and is currently a WORK IN PROGRESS!
Nothing in this repository is ready for deployment, nor is anything final.

**DON'T USE THIS IN ANY OF YOUR PROJECTS (YET)!**

## Common Questions and Answers

#### What operating systems are planned to be supported?

- Mac OS X
- Linux
- Windows
- Android (Arm64v8 and Arm7)
- iOS

#### Will everything be backwards compatible?

All functions will retain their name and functionalities, making any prior projects made in the old repository fully compatible with these new binaries.

#### Will the old plugin still be supported?

Support for the old repository won't change any time soon and will be updated with new features alongside this new repository.

#### Why are you porting this plugin?

Development using godot-cpp has proven to be tedious and the main repository isn't that well maintained anymore. Exporting to Android has long since been a goal of mine, but there's no concrete information nor help on how to do it. In contrast, Kotlin makes it almost a trivial matter, just changing a variable, to export to Android.
