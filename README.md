# Centralization Plugin for Gephi

## Overview
Bearkat Centralization computes multiple graph centralization metrics—including degree, weighted degree, in-degree, out-degree, betweenness, eigenvector, closeness, and harmonic closeness—from a single Statistics report. The plugin also exports node-level values to the Data Laboratory and supports both directed and undirected networks. It includes interpretive notes regarding closeness centralization on disconnected graphs and differences in eigenvector centralization implementations across software packages.


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
Braell Dotson & Dr. Nate Jones
