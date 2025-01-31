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
package com.axelor.apps.account.service;

import com.axelor.apps.account.db.DepositSlip;
import com.axelor.apps.account.db.PaymentVoucher;
import com.axelor.exception.AxelorException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface DepositSlipService {

  /**
   * Get payments into deposit slip.
   *
   * @param depositSlip
   * @throws AxelorException
   */
  List<PaymentVoucher> fetchPaymentVouchers(DepositSlip depositSlip);

  /**
   * Publish deposit slip.
   *
   * @param depositSlip
   * @return
   * @throws AxelorException
   * @throws IOException
   */
  LocalDate publish(DepositSlip depositSlip) throws AxelorException;

  /**
   * Validate deposit slip using value for collection account.
   *
   * @param depositSlip
   * @return
   * @throws AxelorException
   * @throws IOException
   */
  void validate(DepositSlip depositSlip) throws AxelorException;
}
