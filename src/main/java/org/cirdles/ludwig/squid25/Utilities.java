/*
 * Copyright 2006-2017 CIRDLES.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cirdles.ludwig.squid25;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Provides utility functions to support LudwigLibrary.
 *
 * @author James F. Bowring
 */
public class Utilities {

    /**
     * Calculates arithmetic median of array of doubles.
     *
     * @param values a double[] array of values
     * @return median as double
     * @pre values has at least one element
     */
    public static double median(double[] values) {
        double median;
        // enforce precondition
        if (values.length == 0) {
            median = 0.0;
        } else {
            DescriptiveStatistics stats = new DescriptiveStatistics(values);
            median = stats.getPercentile(50);
        }
        return median;
    }

    /**
     * Performs excel-style rounding of double to a given number of significant
     * figures.
     *
     * @param value double to round
     * @param sigFigs count of significant digits for rounding
     * @return double rounded to sigFigs significant digits
     */
    public static double roundedToSize(double value, int sigFigs) {
        BigDecimal valueBDtoSize = BigDecimal.ZERO;
        if (Double.isFinite(value)) {
            BigDecimal valueBD = new BigDecimal(value);
            int newScale = sigFigs - (valueBD.precision() - valueBD.scale());
            valueBDtoSize = valueBD.setScale(newScale, RoundingMode.HALF_UP);
        }
        return valueBDtoSize.doubleValue();
    }

    /**
     * Performs excel-style rounding of double[] to a given number of
     * significant figures.
     *
     * @param values double[] to round
     * @param sigFigs count of significant digits for rounding
     * @return double[] rounded to sigFigs significant digits
     */
    public static double[] roundedToSize(double[] values, int sigFigs) {
        double[] retVal = new double[values.length];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = roundedToSize(values[i], sigFigs);
        }
        return retVal;
    }

    /**
     * Performs excel-style rounding of double[][] to a given number of
     * significant figures.
     *
     * @param values double[][] to round
     * @param sigFigs count of significant digits for rounding
     * @return double[][] rounded to sigFigs significant digits
     */
    public static double[][] roundedToSize(double[][] values, int sigFigs) {
        double[][] retVal = new double[values.length][];
        for (int i = 0; i < retVal.length; i++) {
            retVal[i] = roundedToSize(values[i], sigFigs);
        }
        return retVal;
    }
}
