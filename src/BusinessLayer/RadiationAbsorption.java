package BusinessLayer;

import java.util.Random;

public class RadiationAbsorption extends ClimateMeasurement {
    private RadiationIntensity radiationIntensity;
    private double unitAbsorptionValue;

    public RadiationAbsorption(int year, String month) {
        super(year, month);
        initializeMeasurements();
    }

    private void initializeMeasurements() {
        Random random = new Random();
        unitAbsorptionValue = 5.0 + (20.0 - 5.0) * random.nextDouble();
        radiationIntensity = RadiationIntensity.values()[random.nextInt(RadiationIntensity.values().length)];
    }

	public RadiationIntensity getRadiationIntensity() {
		return radiationIntensity;
	}

	public double getUnitAbsorptionValue() {
		return unitAbsorptionValue;
	}
	
	@Override
    public String toString() {
        return "Radiation Absorption Measurement - Year: " + getYear() + ", Month: " + getMonth()
                + ", Radiation Intensity: " + radiationIntensity + ", Unit Absorption Value: " + unitAbsorptionValue;
    }
}