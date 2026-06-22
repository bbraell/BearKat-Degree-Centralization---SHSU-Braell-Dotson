# Centralization Plugin for Gephi

## Overview
This plugin adds a new statistic to Gephi that computes **graph centralization** on a 0–1 scale based on degree centrality.

Centralization measures how concentrated a network is around its most connected node.


## Supported Statistics

- Degree Centralization
- Weighted Degree Centralization
- In-Degree Centralization
- Out-Degree Centralization
- Betweenness Centralization
- Eigenvector Centralization
- Closeness Centralization
- Harmonic Closeness Centralization

## Installation
1. Open Gephi
2. Go to **Tools → Plugins → Downloaded**
3. Click **Add Plugins**
4. Select the `.nbm` file
5. Restart Gephi

## Usage
1. Import a graph into Gephi
2. Go to **Statistics panel**
3. Run the Centralization statistic to calculate all supported centralization metrics simultaneously.
4. View results in the report window

## Example Results
- Star network → Centralization ≈ 1.0  
- Balanced network → Centralization ≈ 0.0  

## Author
Braell Dotson
