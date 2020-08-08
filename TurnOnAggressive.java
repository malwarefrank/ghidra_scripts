import ghidra.app.script.GhidraScript;

import java.util.Map;

public class TurnOnAggressive extends GhidraScript {
	private static final String AGGRESSIVE = "Aggressive Instruction Finder";

	@Override
	protected void run() throws Exception {
		Map<String, String> options = getCurrentAnalysisOptionsAndValues(currentProgram);
		setAnalysisOption(currentProgram, AGGRESSIVE, "true");
	}
}
