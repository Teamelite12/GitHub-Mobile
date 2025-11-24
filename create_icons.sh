#!/bin/bash
# Create simple placeholder icons using ImageMagick (if available) or just touch empty files
for dir in app/src/main/res/mipmap-{m,h,xh,xxh,xxxh}dpi; do
    # Create a simple text file as placeholder (will be replaced with actual icon later)
    echo "Placeholder icon" > "$dir/ic_launcher.png"
done
