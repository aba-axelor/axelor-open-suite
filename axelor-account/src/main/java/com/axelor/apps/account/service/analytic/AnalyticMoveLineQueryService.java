/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2022 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.account.service.analytic;

import com.axelor.apps.account.db.AnalyticAccount;
import com.axelor.apps.account.db.AnalyticAxis;
import com.axelor.apps.account.db.AnalyticMoveLine;
import com.axelor.apps.account.db.AnalyticMoveLineQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AnalyticMoveLineQueryService {

  public String getAnalyticMoveLineQuery(AnalyticMoveLineQuery analyticMoveLineQuery);

  public Set<AnalyticMoveLine> analyticMoveLineReverses(
      AnalyticMoveLineQuery analyticMoveLineQuery, List<AnalyticMoveLine> analyticMoveLines);

  public Set<AnalyticMoveLine> createAnalyticaMoveLines(
      AnalyticMoveLineQuery analyticMoveLineQuery, List<AnalyticMoveLine> analyticMoveLines);

  public Map<AnalyticAxis, AnalyticAccount> getReverseRules(
      AnalyticMoveLineQuery analyticMoveLineQuery);

  List<AnalyticAxis> getAvailableAnalyticAxes(
      AnalyticMoveLineQuery analyticMoveLineQuery, boolean isReverseQuery);
}
