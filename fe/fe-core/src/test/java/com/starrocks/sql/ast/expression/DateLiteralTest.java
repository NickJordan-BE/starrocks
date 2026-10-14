// Copyright 2021-present StarRocks, Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.starrocks.sql.ast.expression;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateLiteralTest {

    @Test
    public void testCompareLiteralMicro() {
        DateLiteral thisEqual = new DateLiteral(2026, 10, 8, 8, 30, 18, 282585);
        DateLiteral otherEqual = new DateLiteral(2026, 10, 8, 8, 30, 18, 282585);
        Assertions.assertEquals(0, thisEqual.compareLiteral(otherEqual));

        DateLiteral thisLess = new DateLiteral(2026, 10, 8, 8, 30, 18, 282585);
        DateLiteral otherLess = new DateLiteral(2026, 10, 8, 8, 30, 18, 282586);
        Assertions.assertEquals(-1, thisLess.compareLiteral(otherLess));

        DateLiteral thisGreater = new DateLiteral(2026, 10, 8, 8, 30, 18, 282586);
        DateLiteral otherGreater = new DateLiteral(2026, 10, 8, 8, 30, 18, 282585);
        Assertions.assertEquals(1, thisGreater.compareLiteral(otherGreater));

        DateLiteral thisLessSec = new DateLiteral(2026, 10, 8, 8, 30, 17, 282585);
        DateLiteral otherLessSec = new DateLiteral(2026, 10, 8, 8, 30, 18, 282585);
        Assertions.assertEquals(-1, thisLessSec.compareLiteral(otherLessSec));
        
        DateLiteral thisGreaterSec = new DateLiteral(2026, 10, 8, 8, 30, 18, 282586);
        DateLiteral otherGreaterSec = new DateLiteral(2026, 10, 8, 8, 30, 17, 282585);
        Assertions.assertEquals(1, thisGreaterSec.compareLiteral(otherGreaterSec));
    }

    @Test
    public void testCompareLiteralMili() {
        DateLiteral thisEqual = new DateLiteral(2026, 10, 8, 8, 30, 18, 282);
        DateLiteral otherEqual = new DateLiteral(2026, 10, 8, 8, 30, 18, 282);
        Assertions.assertEquals(0, thisEqual.compareLiteral(otherEqual));

        DateLiteral thisLess = new DateLiteral(2026, 10, 8, 8, 30, 18, 282);
        DateLiteral otherLess = new DateLiteral(2026, 10, 8, 8, 30, 18, 283);
        Assertions.assertEquals(-1, thisLess.compareLiteral(otherLess));

        DateLiteral thisGreater = new DateLiteral(2026, 10, 8, 8, 30, 18, 283);
        DateLiteral otherGreater = new DateLiteral(2026, 10, 8, 8, 30, 18, 282);
        Assertions.assertEquals(1, thisGreater.compareLiteral(otherGreater));
    }

    @Test
    public void testCompareLiteralLongOnly() {
        DateLiteral thisEqual = new DateLiteral(2026, 10, 8, 8, 30, 18, 0);
        DateLiteral otherEqual = new DateLiteral(2026, 10, 8, 8, 30, 18, 0);
        Assertions.assertEquals(0, thisEqual.compareLiteral(otherEqual));

        DateLiteral thisLess = new DateLiteral(2026, 10, 8, 8, 30, 18, 0);
        DateLiteral otherLess = new DateLiteral(2026, 10, 8, 8, 30, 19, 0);
        Assertions.assertEquals(-1, thisLess.compareLiteral(otherLess));

        DateLiteral thisGreater = new DateLiteral(2026, 10, 8, 8, 30, 19, 0);
        DateLiteral otherGreater = new DateLiteral(2026, 10, 8, 8, 30, 18, 0);
        Assertions.assertEquals(1, thisGreater.compareLiteral(otherGreater));
    }

    @Test
    public void testCompareLiteralNonDate() {
        DateLiteral thisEqual = new DateLiteral(0, 0, 0, 0, 0, 0, 0);
        LiteralExpr otherEqual = new IntLiteral(0);
        Assertions.assertEquals(0, thisEqual.compareLiteral(otherEqual));

        DateLiteral thisLess = new DateLiteral(0, 0, 0, 0, 0, 3, 0);
        LiteralExpr otherLess = new IntLiteral(5);
        Assertions.assertEquals(-1, thisLess.compareLiteral(otherLess));

        DateLiteral thisGreater = new DateLiteral(0, 0, 0, 0, 0, 10, 0);
        LiteralExpr otherGreater = new IntLiteral(5);
        Assertions.assertEquals(1, thisGreater.compareLiteral(otherGreater));
    }
}
