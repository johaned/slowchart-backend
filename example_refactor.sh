#!/bin/bash
find . -name "*.example" -exec bash -c 'name="{}"; cp "$name" "${name%.example}"' \;
