#!/bin/sh

convert rails_src/align_90_east.png -crop 3x3@ +repage -colorspace srgb rails/align_90_east_%d.png
convert rails_src/align_90_west.png -crop 3x3@ +repage -colorspace srgb rails/align_90_west_%d.png
convert rails_src/align_0_north.png -crop 3x3@ +repage -colorspace srgb rails/align_0_north_%d.png
convert rails_src/align_0_south.png -crop 3x3@ +repage -colorspace srgb rails/align_0_south_%d.png

cp -f rails_src/inc* rails/
cp -f rails_src/std* rails/






# Correct blank pngs:

cp -f rails_src/blank.png rails/align_0_north_0.png
cp -f rails_src/blank.png rails/align_0_north_8.png

cp -f rails_src/blank.png rails/align_0_south_2.png
cp -f rails_src/blank.png rails/align_0_south_6.png

cp -f rails_src/blank.png rails/align_90_east_0.png
cp -f rails_src/blank.png rails/align_90_east_8.png

cp -f rails_src/blank.png rails/align_90_west_2.png
cp -f rails_src/blank.png rails/align_90_west_6.png

