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
package com.axelor.apps.account.service.move;

import com.axelor.apps.account.db.MoveLine;
import com.axelor.exception.AxelorException;
import java.time.LocalDate;

public interface MoveLineInvoiceTermService {
  void generateDefaultInvoiceTerm(
      MoveLine moveLine, LocalDate singleTermDueDate, boolean canCreateHolbackMoveLine)
      throws AxelorException;

  void generateDefaultInvoiceTerm(MoveLine moveLine, boolean canCreateHolbackMoveLine)
      throws AxelorException;

  void updateInvoiceTermsParentFields(MoveLine moveLine);

  void recreateInvoiceTerms(MoveLine moveLine) throws AxelorException;

  void setDueDateFromInvoiceTerms(MoveLine moveLine);
}
