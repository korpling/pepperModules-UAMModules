/**
 * Copyright 2009 Humboldt-Universität zu Berlin, INRIA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package de.hu_berlin.german.korpling.saltnpepper.model.uam.impl;

import de.hu_berlin.german.korpling.saltnpepper.model.uam.Layer;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.Segment;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMDocument;
import de.hu_berlin.german.korpling.saltnpepper.model.uam.UAMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Layer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.impl.LayerImpl#getSegments <em>Segments</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.impl.LayerImpl#getLang <em>Lang</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.impl.LayerImpl#getComplete <em>Complete</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.impl.LayerImpl#getUamDocument <em>Uam Document</em>}</li>
 *   <li>{@link de.hu_berlin.german.korpling.saltnpepper.model.uam.impl.LayerImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LayerImpl extends EObjectImpl implements Layer {
	/**
	 * The cached value of the '{@link #getSegments() <em>Segments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSegments()
	 * @generated
	 * @ordered
	 */
	protected EList<Segment> segments;

	/**
	 * The default value of the '{@link #getLang() <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLang()
	 * @generated
	 * @ordered
	 */
	protected static final String LANG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLang() <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLang()
	 * @generated
	 * @ordered
	 */
	protected String lang = LANG_EDEFAULT;

	/**
	 * The default value of the '{@link #getComplete() <em>Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComplete()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPLETE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComplete() <em>Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComplete()
	 * @generated
	 * @ordered
	 */
	protected String complete = COMPLETE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LayerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UAMPackage.Literals.LAYER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Segment> getSegments() {
		if (segments == null) {
			segments = new EObjectContainmentWithInverseEList<Segment>(Segment.class, this, UAMPackage.LAYER__SEGMENTS, UAMPackage.SEGMENT__LAYER);
		}
		return segments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLang(String newLang) {
		String oldLang = lang;
		lang = newLang;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.LAYER__LANG, oldLang, lang));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComplete() {
		return complete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComplete(String newComplete) {
		String oldComplete = complete;
		complete = newComplete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.LAYER__COMPLETE, oldComplete, complete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UAMDocument getUamDocument() {
		if (eContainerFeatureID() != UAMPackage.LAYER__UAM_DOCUMENT) return null;
		return (UAMDocument)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUamDocument(UAMDocument newUamDocument, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUamDocument, UAMPackage.LAYER__UAM_DOCUMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUamDocument(UAMDocument newUamDocument) {
		if (newUamDocument != eInternalContainer() || (eContainerFeatureID() != UAMPackage.LAYER__UAM_DOCUMENT && newUamDocument != null)) {
			if (EcoreUtil.isAncestor(this, newUamDocument))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUamDocument != null)
				msgs = ((InternalEObject)newUamDocument).eInverseAdd(this, UAMPackage.UAM_DOCUMENT__LAYERS, UAMDocument.class, msgs);
			msgs = basicSetUamDocument(newUamDocument, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.LAYER__UAM_DOCUMENT, newUamDocument, newUamDocument));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.LAYER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UAMPackage.LAYER__SEGMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSegments()).basicAdd(otherEnd, msgs);
			case UAMPackage.LAYER__UAM_DOCUMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUamDocument((UAMDocument)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UAMPackage.LAYER__SEGMENTS:
				return ((InternalEList<?>)getSegments()).basicRemove(otherEnd, msgs);
			case UAMPackage.LAYER__UAM_DOCUMENT:
				return basicSetUamDocument(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case UAMPackage.LAYER__UAM_DOCUMENT:
				return eInternalContainer().eInverseRemove(this, UAMPackage.UAM_DOCUMENT__LAYERS, UAMDocument.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UAMPackage.LAYER__SEGMENTS:
				return getSegments();
			case UAMPackage.LAYER__LANG:
				return getLang();
			case UAMPackage.LAYER__COMPLETE:
				return getComplete();
			case UAMPackage.LAYER__UAM_DOCUMENT:
				return getUamDocument();
			case UAMPackage.LAYER__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UAMPackage.LAYER__SEGMENTS:
				getSegments().clear();
				getSegments().addAll((Collection<? extends Segment>)newValue);
				return;
			case UAMPackage.LAYER__LANG:
				setLang((String)newValue);
				return;
			case UAMPackage.LAYER__COMPLETE:
				setComplete((String)newValue);
				return;
			case UAMPackage.LAYER__UAM_DOCUMENT:
				setUamDocument((UAMDocument)newValue);
				return;
			case UAMPackage.LAYER__NAME:
				setName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UAMPackage.LAYER__SEGMENTS:
				getSegments().clear();
				return;
			case UAMPackage.LAYER__LANG:
				setLang(LANG_EDEFAULT);
				return;
			case UAMPackage.LAYER__COMPLETE:
				setComplete(COMPLETE_EDEFAULT);
				return;
			case UAMPackage.LAYER__UAM_DOCUMENT:
				setUamDocument((UAMDocument)null);
				return;
			case UAMPackage.LAYER__NAME:
				setName(NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UAMPackage.LAYER__SEGMENTS:
				return segments != null && !segments.isEmpty();
			case UAMPackage.LAYER__LANG:
				return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
			case UAMPackage.LAYER__COMPLETE:
				return COMPLETE_EDEFAULT == null ? complete != null : !COMPLETE_EDEFAULT.equals(complete);
			case UAMPackage.LAYER__UAM_DOCUMENT:
				return getUamDocument() != null;
			case UAMPackage.LAYER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (lang: ");
		result.append(lang);
		result.append(", complete: ");
		result.append(complete);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //LayerImpl
