package br.com.lucas.pitanga.dto;

public class SimulationResult {
    public String componentType;
    public int outputResult;     // Bit de saída (0 ou 1)
    public String ledStatus;     // Ex: "ON" ou "OFF"

    public SimulationResult() {}

    public SimulationResult(String componentType, int outputResult, String ledStatus) {
        this.componentType = componentType;
        this.outputResult = outputResult;
        this.ledStatus = ledStatus;
    }
}
