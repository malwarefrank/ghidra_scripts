// modeled after https://reverseengineering.stackexchange.com/a/21956

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ghidra.app.plugin.core.script.Ingredient;
import ghidra.app.plugin.core.script.IngredientDescription;
import ghidra.app.script.GatherParamPanel;
import ghidra.app.script.GhidraScript;
import ghidra.app.util.exporter.ExporterException;

import com.google.security.binexport.BinExportExporter;

public class BinExport extends GhidraScript implements Ingredient {
    private static Logger log;

    public BinExport() {
        log = LogManager.getLogger(BinExport.class);
    }

    public void export(String filename) {
        File outputFile = new File(filename);
        BinExportExporter binExporter = new BinExportExporter();
        binExporter.setExporterServiceProvider(state.getTool());

        try {
            binExporter.export(outputFile, currentProgram, null, monitor);
        } catch (IOException e) {
            log.error("Failed writing BinExport as output", e);
        } catch (ExporterException e) {
            log.error("Failed to export with BinExporter", e);
        }
    }

    @Override
    public void run() throws Exception {
        IngredientDescription[] ingredients = getIngredientDescriptions();
        for (IngredientDescription ingredient : ingredients) {
            state.addParameter(ingredient.getID(), ingredient.getLabel(),
                    ingredient.getType(), ingredient.getDefaultValue());
        }

        String[] args = getScriptArgs();
        export(args[0]);
    }

    @Override
    public IngredientDescription[] getIngredientDescriptions() {
        IngredientDescription[] retVal = new IngredientDescription[] {
          new IngredientDescription(
                "BinExportFile", "Output BinExport File", GatherParamPanel.FILE, "")};
        return retVal;
    }
}
