# ghidra scripts

1. **TurnOnAggressive** - a preScript to enable the analyzer *Aggressive Instruction Finder*
2. **BinExport** - a postScript to create a [binexport](https://github.com/google/binexport/tree/master/java/BinExport)

## command-line example

```bash
$GHIDRA_INSTALL_DIR/support/analyzeHeadless PROJECT_DIR PROJECT_NAME -preScript TurnOnAggressive.java -postScript BinExport.java DESTINATION_BINEXPORT_FILE -import BINARY_TO_ANALYZE
```
