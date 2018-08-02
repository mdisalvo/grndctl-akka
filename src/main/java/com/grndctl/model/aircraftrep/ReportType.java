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

import scala.Option;

/**
 *
 */
public enum ReportType {

    AIREP("AIREP"), PIREP("PIREP"), URGENT_PIREP("Urgent PIREP");

    private String val;

    ReportType(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

    public static Option<ReportType> fromString(String val) {
        ReportType rt = null;
        for (ReportType type : ReportType.values()) {
            if (type.toString().equals(val)) {
                rt = type;
                break;
            }
        }

        return Option.apply(rt);
    }

}
