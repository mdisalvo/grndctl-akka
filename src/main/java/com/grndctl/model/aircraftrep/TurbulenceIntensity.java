/**
 * MIT License
 * <p>
 * Copyright (c) 2018 grndctl-akka
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.grndctl.model.aircraftrep;

/**
 *
 */
public enum TurbulenceIntensity {

    LGT("LGT"),
    LGTMOD("LGT-MOD"),
    MOD("MOD"),
    MODSEV("MOD-SEV"),
    SEV("SEV"),
    SEVEXTRM("SEV-EXTRM"),
    EXTRM("EXTRM"),
    NEG("NEG");

    private String val;

    private TurbulenceIntensity(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

    public static TurbulenceIntensity fromString(String val) {
        TurbulenceIntensity[] intensities = TurbulenceIntensity.values();

        TurbulenceIntensity ti = null;
        for (TurbulenceIntensity intensity : intensities) {
            if (intensity.toString().equals(val)) {
                ti = intensity;
                break;
            }
        }

        return ti;
    }
}
