# ProjectCatheter
Catheter Lab is an interface for viewing multipolar catheter data. 

Instructions:

Data input and views:

-First, you need to input data. This needs to be an excel file in .xlsx format. The first row is taken to be catheter labels.

-Upon inputting a file, you will be prompted to include the catheter dimensions, as x by y. These are used for different rotations. 

 If using a catheter with electrodes not arranged in a grid, you can input it as a 1 by n or vice versa, and use the 'single plot' option.
 
-Grid plot opens the plots arranged as a grid, single plot in one column. Currently, rotating the graphs is only possible in grid view.

-Both grid and single views include the option for viewing bipolar plots, or differences between adjacent electrodes.

Markers:

-Clicking a point on any of the graphs highlights that x-value / time point on all graphs and displays the value at that point. 

-To remove the markers, you can click the "Clear Markers" button or click outside the graph area.

Zooming:

-Clicking and dragging to highlight an area zooms in on that area. Additionally, scroll wheel can be used to zoom in or out.

-By default, zooming is restricted to one graph. Pressing the "Zoom All" button links the axes together. 

-"Restore Axis" button resets to default zoom level.

Other functionality:

-"Rotate" rearranges the grid view to correspond to a step rotation. This corresponds to different orientations of the grid catheter. 
 
-The "Hide Name" button toggles graph titles on or off.

Known issues:

-Bipolar view includes some additional unnecessary plots. 

-Some combinations of repeated zooming and resetting may cause the graph titles or data marker labels to appear stretched. 

 This can be reset by closing the graphing window and reopening it from the main window.
 
-An unnecessary empty window is opened during data input. This is harmless and can be closed without issue.
